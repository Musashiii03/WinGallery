# WinGallery v1.0.1 - Updated Release

## 📥 Download

**WinGallery-1.0.0-Windows-Portable-v2.zip** (32 MB)
- No installation required
- No Java installation needed
- Extract and run WinGallery.exe

## ✨ Features

- 📸 **Photo Support** - View images (JPG, PNG, GIF, BMP) - **Always works perfectly**
- 🎬 **Video Support** - View videos (MP4, AVI, MOV, MKV) - **Playback works, thumbnails may vary**
- 🎨 **Masonry Layout** - Pinterest-style responsive grid
- 🔍 **Search & Filter** - Search by filename, filter by media type
- 📊 **Sort Options** - Sort by name or date modified
- 🎬 **Video Player** - Built-in player with controls, loop, and fullscreen
- 📁 **Folder Management** - Add multiple folders, recursive subfolder scanning
- 🌙 **Dark Theme** - Modern dark UI with custom title bar
- ⌨️ **Keyboard Shortcuts** - Arrow keys for navigation, spacebar for play/pause, F11 for fullscreen

## 🚀 How to Use

1. Download `WinGallery-1.0.0-Windows-Portable-v2.zip`
2. Extract the ZIP file to any folder
3. Run `WinGallery.exe`
4. Click "Add Folder" to select your photo/video folders
5. Enjoy browsing your media!
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

### Why This Happens:
JavaFX media support on Windows depends on system codecs. Different machines may have different codec support. Your videos will always play correctly - only the thumbnail generation may vary.

### To Get Better Thumbnail Support:
Convert videos to MP4 with H.264 codec using free tools like:
- HandBrake: https://handbrake.fr/
- VLC Media Player: Media → Convert/Save

## 💻 System Requirements

- Windows 10 or later
- 64-bit system
- No Java installation required (bundled)

## 🆕 What's New in v1.0.1

- ✨ **Session Persistence** - Your added folders are automatically saved and restored when you reopen the app
- ✨ Added placeholder thumbnails for videos that can't generate previews
- 🐛 Better error handling for video thumbnail generation
- 📝 Improved user feedback when thumbnails fail
- 🎯 Videos always play correctly regardless of thumbnail status
- 💾 Session data saved in your user folder (~/.wingallery/)

## 🐛 Known Issues

- Video thumbnails may show placeholder icon on some systems (videos still play fine)
- This is a JavaFX media codec limitation, not a bug

## 📝 License

MIT License - See LICENSE.txt for details

---

**Note:** If you're upgrading from v1.0.0, this version includes better handling for video thumbnails. All functionality remains the same, with improved error handling.
