package com.example.wingallery;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.application.Platform;

import java.io.File;
import java.util.concurrent.CompletableFuture;

/**
 * Utility class for generating thumbnails from images and videos
 */
public class ThumbnailGenerator {
    private static final int THUMBNAIL_WIDTH = 400;
    private static final int THUMBNAIL_HEIGHT = 400;

    /**
     * Generate thumbnail for an image file
     */
    public static Image generateImageThumbnail(File file) {
        try {
            Image image = new Image(file.toURI().toString(), THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, true, true, true);
            return image;
        } catch (Exception e) {
            System.err.println("Error generating thumbnail for image: " + file.getName());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generate thumbnail for a video file (extracts first frame)
     * First tries JavaFX MediaPlayer, then falls back to ffmpeg if available
     */
    public static CompletableFuture<Image> generateVideoThumbnail(File file) {
        CompletableFuture<Image> future = new CompletableFuture<>();
        
        // Try JavaFX MediaPlayer first (bundled with app)
        tryJavaFXThumbnail(file, future);
        
        // Add timeout fallback to placeholder
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000); // Wait 5 seconds
                if (!future.isDone()) {
                    System.out.println("⚠ Timeout generating thumbnail for: " + file.getName());
                    future.complete(createPlaceholderImage());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        return future;
    }
    
    /**
     * Create a placeholder image for videos that can't generate thumbnails
     */
    private static Image createPlaceholderImage() {
        try {
            // Create a simple colored rectangle as placeholder
            javafx.scene.canvas.Canvas canvas = new javafx.scene.canvas.Canvas(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT);
            javafx.scene.canvas.GraphicsContext gc = canvas.getGraphicsContext2D();
            
            // Dark gray background
            gc.setFill(javafx.scene.paint.Color.rgb(60, 60, 60));
            gc.fillRect(0, 0, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT);
            
            // Play icon
            gc.setFill(javafx.scene.paint.Color.rgb(200, 200, 200));
            double centerX = THUMBNAIL_WIDTH / 2.0;
            double centerY = THUMBNAIL_HEIGHT / 2.0;
            double size = 80;
            
            // Triangle play button
            gc.fillPolygon(
                new double[]{centerX - size/2, centerX + size/2, centerX - size/2},
                new double[]{centerY - size/2, centerY, centerY + size/2},
                3
            );
            
            // Take snapshot
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(javafx.scene.paint.Color.TRANSPARENT);
            WritableImage placeholder = canvas.snapshot(params, null);
            
            return placeholder;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Try to generate thumbnail using ffmpeg command line tool
     */
    private static Image tryFfmpegThumbnail(File videoFile) {
        try {
            // Create temp file for thumbnail
            File tempThumb = File.createTempFile("thumb_", ".jpg");
            tempThumb.deleteOnExit();
            
            // Optimized ffmpeg command - seek BEFORE input for speed
            ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg",
                "-ss", "1",  // Seek to 1 second BEFORE input (much faster)
                "-i", videoFile.getAbsolutePath(),
                "-vframes", "1",  // Extract only 1 frame
                "-vf", "scale=" + THUMBNAIL_WIDTH + ":" + THUMBNAIL_HEIGHT + ":force_original_aspect_ratio=decrease",
                "-q:v", "2",  // High quality JPEG
                "-y",  // Overwrite output
                tempThumb.getAbsolutePath()
            );
            pb.redirectErrorStream(true);
            
            Process process = pb.start();
            // Consume output to prevent blocking
            java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(process.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                // Consume output silently
            }
            
            boolean finished = process.waitFor(10, java.util.concurrent.TimeUnit.SECONDS);
            
            if (finished && process.exitValue() == 0 && tempThumb.exists() && tempThumb.length() > 0) {
                Image thumbnail = new Image(tempThumb.toURI().toString());
                if (thumbnail.getWidth() > 0 && thumbnail.getHeight() > 0) {
                    System.out.println("✓ Thumbnail generated: " + videoFile.getName());
                    return thumbnail;
                }
            } else if (!finished) {
                process.destroyForcibly();
                System.out.println("✗ Timeout: " + videoFile.getName());
            }
        } catch (java.io.IOException e) {
            System.out.println("✗ ffmpeg not found - install ffmpeg to enable video thumbnails");
        } catch (Exception e) {
            System.out.println("✗ Error: " + videoFile.getName() + " - " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Try to generate thumbnail using JavaFX MediaPlayer
     */
    private static void tryJavaFXThumbnail(File file, CompletableFuture<Image> future) {
        Platform.runLater(() -> {
            MediaPlayer mediaPlayer = null;
            try {
                Media media = new Media(file.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                MediaPlayer finalMediaPlayer = mediaPlayer;
                
                // Flag to track if snapshot was taken
                final boolean[] snapshotTaken = {false};
                final MediaView[] mediaViewHolder = {null};
                
                mediaPlayer.setOnReady(() -> {
                    try {
                        // Create MediaView only when ready
                        MediaView mediaView = new MediaView(finalMediaPlayer);
                        mediaView.setFitWidth(THUMBNAIL_WIDTH);
                        mediaView.setFitHeight(THUMBNAIL_HEIGHT);
                        mediaView.setPreserveRatio(true);
                        mediaViewHolder[0] = mediaView;
                        
                        // Seek to 0.5 seconds to avoid black frames
                        finalMediaPlayer.seek(javafx.util.Duration.millis(500));
                    } catch (Exception e) {
                        System.err.println("⚠ Error creating MediaView for: " + file.getName());
                        finalMediaPlayer.dispose();
                        if (!future.isDone()) {
                            future.complete(createPlaceholderImage());
                        }
                    }
                });
                
                // Listen for when seeking is complete
                mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                    if (!snapshotTaken[0] && newTime.toMillis() >= 400 && mediaViewHolder[0] != null) {
                        snapshotTaken[0] = true;
                        
                        // Delay to ensure frame is rendered
                        javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.millis(300));
                        pause.setOnFinished(e -> {
                            try {
                                if (mediaViewHolder[0] != null) {
                                    SnapshotParameters params = new SnapshotParameters();
                                    WritableImage snapshot = mediaViewHolder[0].snapshot(params, null);
                                    
                                    if (snapshot != null && snapshot.getWidth() > 0 && snapshot.getHeight() > 0) {
                                        future.complete(snapshot);
                                    } else {
                                        future.complete(null);
                                    }
                                } else {
                                    future.complete(null);
                                }
                            } catch (Exception ex) {
                                future.complete(null);
                            } finally {
                                finalMediaPlayer.dispose();
                            }
                        });
                        pause.play();
                    }
                });
                
                mediaPlayer.setOnError(() -> {
                    System.err.println("⚠ Media error for: " + file.getName() + " - " + finalMediaPlayer.getError());
                    finalMediaPlayer.dispose();
                    if (!future.isDone()) {
                        future.complete(createPlaceholderImage());
                    }
                });
                
                // Timeout fallback
                MediaPlayer timeoutPlayer = mediaPlayer;
                javafx.animation.PauseTransition timeout = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(3));
                timeout.setOnFinished(e -> {
                    if (!future.isDone()) {
                        timeoutPlayer.dispose();
                        future.complete(null);
                    }
                });
                timeout.play();
                
            } catch (Exception e) {
                if (mediaPlayer != null) {
                    mediaPlayer.dispose();
                }
                future.complete(null);
            }
        });
    }

    /**
     * Check if file is a supported image format
     */
    public static boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || 
               name.endsWith(".png") || name.endsWith(".gif") || 
               name.endsWith(".bmp");
    }

    /**
     * Check if file is a supported video format
     */
    public static boolean isVideoFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".mp4") || name.endsWith(".avi") || 
               name.endsWith(".mov") || name.endsWith(".mkv") ||
               name.endsWith(".m4v") || name.endsWith(".flv");
    }
}
