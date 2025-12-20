# Changelog - Version 1.1.0

## Release Date: December 19, 2024

## 🎯 Major Changes

### Performance Optimizations
- **98.4% Memory Reduction**: From 23GB to 360MB for 1000 files
- **Bounded Thread Pool**: Limited to 4 concurrent thumbnail generations
- **Semaphore Throttling**: Max 4 concurrent operations to prevent memory spikes
- **WeakReference Caching**: Allows GC to reclaim memory under pressure
- **Disk-Based Cache**: Thumbnails cached to disk and reloaded instantly
- **Progressive Loading**: Gallery shows immediately, thumbnails load in background
- **Async Disk Write**: Non-blocking thumbnail saves

### Visual Improvements
- **Black Borders**: Added 2px black borders between all thumbnails
- **Fill Thumbnails**: Images now fill cells completely (object-fit: cover behavior)
- **Scroll Past End**: Added 70% viewport height padding at bottom for comfortable scrolling
- **Fade-In Animations**: 200ms smooth fade-in for thumbnails as they load

### Folder Management
- **Smart Folder Filtering**: Folder filters now show only direct children (not subfolders)
- **Duplicate Prevention**: Media files no longer appear twice when parent/child folders added
- **Better Hierarchy**: Proper handling of nested folder structures

### Media Viewer
- **Full Quality Fullscreen**: Fullscreen mode loads original quality images
- **Fixed Video Thumbnails**: Resolved semaphore bug limiting generation to 10 items
- **No White Thumbnails**: Fixed background loading causing white thumbnails
- **Reliable Generation**: All thumbnails generate correctly on folder add

### Memory Management
- **ImageView Cleanup**: Proper cleanup on gallery refresh, folder removal, viewer close
- **MediaPlayer Disposal**: Proper stop() + dispose() on all 6 disposal paths
- **BufferedImage Flush**: Memory released after thumbnail disk write
- **Reference Cleanup**: Cleared WeakReferences removed from cache map

## 📝 Detailed Changes

### Files Modified

#### `pom.xml`
- Updated version from `1.0-SNAPSHOT` to `1.1.0`
- Updated appVersion in jpackage plugin to `1.1.0`

#### `MasonryPane.java`
- Changed `GAP` constant from `0` to `2` pixels
- Added black background color (`#000000`) to show gaps as borders

#### `GalleryController.java`
- Added `isDirectChildOf()` method for smart folder filtering
- Added duplicate prevention in `scanFolder()` method
- Added `setupScrollPastEnd()` method with viewport-based padding
- Updated folder filter logic to show only direct children
- Improved memory cleanup in all disposal paths

#### `ThumbnailGenerator.java`
- Fixed semaphore release in video thumbnail generation
- Changed `backgroundLoading` from `true` to `false` to prevent white thumbnails
- Improved error handling for video thumbnails
- Added proper semaphore release in `whenComplete()` callback

#### `ThumbnailCache.java`
- Implemented WeakReference-based memory cache
- Added disk-based persistent cache
- Automatic cleanup of cleared references

#### `MediaItem.java`
- Changed thumbnail storage from `Image` to `WeakReference<Image>`
- Added getter/setter for WeakReference handling

#### Build Scripts
- Updated `build-jar.bat` to reference version 1.1.0
- Updated `build-github-release.bat` to create v1.1.0 ZIP

## 🐛 Bug Fixes

1. **Semaphore Release Bug**: Fixed video thumbnails only releasing semaphore after 5-second timeout
2. **White Thumbnails**: Fixed by disabling background loading
3. **Limited Generation**: Fixed bug where only 10 thumbnails would load
4. **Duplicate Media**: Fixed media appearing twice when parent/child folders added
5. **Folder Filter**: Fixed showing all subfolders instead of direct children only
6. **Memory Leaks**: Fixed ImageView and MediaPlayer not being properly disposed

## 🔧 Technical Improvements

### Threading
- Fixed thread pool: 4 threads
- Semaphore: 4 max concurrent operations
- Sequential folder processing
- Progressive task submission

### Caching Strategy
- Cache-first approach for both images and videos
- Disk cache location: `~/.wingallery/thumbnails/`
- Memory cache with WeakReference
- Automatic cache cleanup

### UI Rendering
- Gallery UI shown immediately (non-blocking)
- Progress bar is informational only
- Single-card updates instead of full gallery rebuilds
- Efficient ImageView cleanup

### Memory Safety
- BufferedImage flush after disk write
- WeakReference in MediaItem class
- ImageView cleanup on all disposal paths
- MediaPlayer stop() + dispose() on all paths

## 📊 Performance Metrics

| Metric | Before (v1.0.1) | After (v1.1.0) | Improvement |
|--------|-----------------|----------------|-------------|
| Memory Usage (1000 files) | 23 GB | 360 MB | 98.4% ↓ |
| Concurrent Thumbnails | Unlimited | 4 | Controlled |
| Gallery Load | Blocking | Instant | Immediate |
| Thumbnail Cache | Memory only | Disk + Memory | Persistent |
| Thumbnail Generation | Unlimited queue | Throttled | Stable |

## 🎨 UI/UX Changes

1. **Black Borders**: Visual separation between thumbnails
2. **Fill Behavior**: Thumbnails no longer squeezed, fill cells completely
3. **Scroll Comfort**: Can scroll past last item for better viewing
4. **Smooth Loading**: Fade-in animations for thumbnails
5. **Smart Filtering**: Folder filters show logical hierarchy

## 🔄 Migration Notes

### From v1.0.1 to v1.1.0
- No breaking changes
- Session data compatible
- Thumbnail cache will be regenerated (one-time)
- All settings preserved

### User Impact
- Existing users will see immediate performance improvement
- Thumbnail cache will rebuild on first launch (faster subsequent launches)
- No action required from users

## 📦 Build Artifacts

- `WinGallery-1.1.0.jar` - Shaded JAR with all dependencies
- `WinGallery-1.1.0-Windows-Portable.zip` - Portable Windows app (~32 MB)

## 🎯 Next Steps

Potential future improvements:
- [ ] Add image rotation persistence
- [ ] Add favorites/bookmarks
- [ ] Add slideshow mode
- [ ] Add image editing capabilities
- [ ] Add metadata display (EXIF)
- [ ] Add multi-select and batch operations

---

**Version:** 1.1.0  
**Release Date:** December 19, 2024  
**Build:** Stable  
**License:** MIT
