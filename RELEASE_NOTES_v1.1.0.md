# WinGallery v1.1.0 - Major Performance & UX Update

## 📥 Download

**WinGallery-1.1.0-Windows-Portable.zip** (~32 MB)
- No installation required
- No Java installation needed
- Extract and run WinGallery.exe

## 🆕 What's New in v1.1.0

### 🎨 Visual Improvements
- ✨ **Black Borders Between Images** - Clean separation between thumbnails with thin black borders
- ✨ **Fill Thumbnails** - Images now fill thumbnail cells completely (like CSS object-fit: cover) instead of being squeezed
- ✨ **Scroll Past End** - Scroll beyond the last item for better viewing comfort

### 🚀 Performance Optimizations
- ⚡ **98.4% Memory Reduction** - Reduced memory usage from 23GB to 360MB for 1000 files
- ⚡ **Bounded Thread Pool** - Limited to 4 concurrent thumbnail generations
- ⚡ **Semaphore Throttling** - Prevents memory spikes during thumbnail generation
- ⚡ **WeakReference Caching** - Allows garbage collection to reclaim memory when needed
- ⚡ **Disk-Based Thumbnail Cache** - Thumbnails generated once and cached to disk
- ⚡ **Progressive Loading** - Gallery shows immediately, thumbnails load in background
- ⚡ **Fade-In Animations** - Smooth 200ms fade-in for thumbnails as they load

### 📁 Folder Management Improvements
- ✨ **Smart Folder Filtering** - Clicking a folder filter now shows only direct children (not subfolders)
- ✨ **Duplicate Prevention** - Media files no longer appear twice when parent and child folders are added
- ✨ **Better Subfolder Handling** - Recursive scanning with proper folder hierarchy

### 🎬 Media Viewer Enhancements
- ✨ **Full Quality in Fullscreen** - Fullscreen mode now loads original quality images (not optimized versions)
- ✨ **Better Video Thumbnails** - Fixed semaphore release bug that limited thumbnail generation to 10 items
- ✨ **No White Thumbnails** - Fixed background loading issue that caused white thumbnails
- ✨ **Reliable Thumbnail Generation** - All thumbnails now generate correctly on folder add

### 🧹 Memory Management
- ✨ **ImageView Cleanup** - Proper cleanup on gallery refresh, folder removal, and viewer close
- ✨ **MediaPlayer Disposal** - Proper stop() + dispose() on all 6 disposal paths
- ✨ **BufferedImage Flush** - Memory released after thumbnail disk write
- ✨ **Async Disk Write** - Non-blocking thumbnail saves

## ✨ Core Features

- 📸 **Photo Support** - View images (JPG, PNG, GIF, BMP)
- 🎬 **Video Support** - View videos (MP4, AVI, MOV, MKV)
- 🎨 **Masonry Layout** - Pinterest-style responsive grid with black borders
- 🔍 **Search & Filter** - Search by filename, filter by media type
- 📊 **Sort Options** - Sort by name or date modified
- 🎬 **Video Player** - Built-in player with controls, loop, and fullscreen
- 📁 **Folder Management** - Add multiple folders, recursive subfolder scanning
- 🌙 **Dark Theme** - Modern dark UI with custom title bar
- ⌨️ **Keyboard Shortcuts** - Arrow keys for navigation, spacebar for play/pause, F11 for fullscreen
- 💾 **Session Persistence** - Your folders are automatically saved and restored

## 🚀 How to Use

1. Download `WinGallery-1.1.0-Windows-Portable.zip`
2. Extract the ZIP file to any folder
3. Run `WinGallery.exe`
4. Click "Add Folder" to select your photo/video folders
5. Enjoy browsing your media with improved performance!
6. **Your folders are automatically saved** - next time you open the app, your folders will be restored!

## 📹 About Video Thumbnails

### What Works:
✅ **All image thumbnails** - JPG, PNG, GIF, BMP work perfectly
✅ **Video playback** - All supported video formats play correctly
✅ **MP4 videos** - Thumbnails usually work for H.264 encoded MP4 files

### What May Show Placeholder:
⚠️ **Some video formats** - MKV, AVI, MOV may show a gray play icon instead of thumbnail
- This is due to codec limitations in JavaFX
- **Videos still play normally** - only the thumbnail preview is affected
- The app gracefully shows a placeholder icon instead of failing

## 💻 System Requirements

- Windows 10 or later
- 64-bit system
- No Java installation required (bundled)

## 🐛 Bug Fixes in v1.1.0

- 🐛 Fixed semaphore release bug that limited thumbnail generation to 10 items
- 🐛 Fixed white thumbnails caused by background loading
- 🐛 Fixed thumbnails not generating on new folder add
- 🐛 Fixed duplicate media items when parent and child folders are added
- 🐛 Fixed folder filter showing all subfolders instead of direct children only
- 🐛 Fixed memory leaks in ImageView and MediaPlayer cleanup

## 📊 Performance Comparison

| Metric | v1.0.1 | v1.1.0 | Improvement |
|--------|--------|--------|-------------|
| Memory (1000 files) | 23 GB | 360 MB | **98.4% reduction** |
| Thumbnail Generation | Unlimited | 4 concurrent | **Controlled** |
| Gallery Load Time | Blocking | Instant | **Immediate** |
| Thumbnail Cache | Memory only | Disk + Memory | **Persistent** |

## 📝 License

MIT License - See LICENSE.txt for details

---

**Upgrade Recommended:** If you're using v1.0.1 or earlier, this version provides massive performance improvements and better user experience. All your settings and folders will be preserved.
