# 🚀 WinGallery v1.1.0 Release Guide

## ✅ Build Complete!

Your build was successful! Here's what you have:

### 📦 Build Artifacts

1. **Portable App Folder:**
   - Location: `target\portable\WinGallery\`
   - Contains: `WinGallery.exe` and runtime files
   - Size: ~120 MB

2. **ZIP for GitHub Release:**
   - Location: `target\WinGallery-1.1.0-Windows-Portable.zip`
   - Size: ~32 MB
   - Ready to upload to GitHub

## 🧪 Test Before Release

Before uploading to GitHub, test the build locally:

```cmd
cd target\portable\WinGallery
WinGallery.exe
```

### Test Checklist:
- [ ] App launches without errors
- [ ] Add a folder with images and videos
- [ ] Verify black borders between thumbnails
- [ ] Verify thumbnails fill cells (not squeezed)
- [ ] Test scroll past end functionality
- [ ] Click folder filter - should show only direct children
- [ ] Add parent and child folders - no duplicates
- [ ] Open fullscreen - should show full quality
- [ ] Video thumbnails generate correctly
- [ ] Close and reopen - folders should be restored

## 📤 Upload to GitHub

### Step 1: Go to GitHub Releases
Open your browser and go to:
```
https://github.com/YOUR_USERNAME/WinGallery/releases/new
```

Replace `YOUR_USERNAME` with your actual GitHub username.

### Step 2: Create New Tag
1. In the "Choose a tag" dropdown, type: `v1.1.0`
2. Click "Create new tag: v1.1.0 on publish"

### Step 3: Fill Release Information

**Release Title:**
```
WinGallery v1.1.0 - Major Performance & UX Update
```

**Description:**
Copy and paste the content from: `RELEASE_NOTES_v1.1.0.md`

Or use this summary:
```markdown
## 🆕 What's New

### Performance
- ⚡ 98.4% memory reduction (23GB → 360MB for 1000 files)
- ⚡ Bounded thread pool with semaphore throttling
- ⚡ Disk-based thumbnail cache
- ⚡ Progressive loading with fade-in animations

### Visual
- ✨ Black borders between images
- ✨ Fill thumbnails (no squeezing)
- ✨ Scroll past end

### Features
- ✨ Smart folder filtering (direct children only)
- ✨ Duplicate prevention
- ✨ Full quality in fullscreen
- ✨ Fixed video thumbnail generation

## 📥 Download
Download `WinGallery-1.1.0-Windows-Portable.zip`, extract, and run `WinGallery.exe`

No installation or Java required!
```

### Step 4: Upload ZIP File
1. Drag and drop: `target\WinGallery-1.1.0-Windows-Portable.zip`
2. Wait for upload to complete (should take 1-2 minutes)

### Step 5: Publish
1. Check "Set as the latest release"
2. Click "Publish release"

## 🎉 Release Published!

Your release is now live! Share the link:
```
https://github.com/YOUR_USERNAME/WinGallery/releases/tag/v1.1.0
```

## 📢 Announce the Release

### On GitHub
The release is automatically visible on your repository's main page.

### Update README.md
Update the download link in your README to point to v1.1.0:
```markdown
## Download

[Download WinGallery v1.1.0](https://github.com/YOUR_USERNAME/WinGallery/releases/tag/v1.1.0)
```

### Social Media (Optional)
Share on Twitter, Reddit, etc.:
```
🎉 WinGallery v1.1.0 is out!

✨ 98.4% memory reduction
✨ Black borders between images
✨ Smart folder filtering
✨ Full quality fullscreen

Download: [your-link]

#JavaFX #OpenSource #PhotoGallery
```

## 📊 What Changed in v1.1.0

### Performance Improvements
- Memory usage: 23GB → 360MB (98.4% reduction)
- Bounded thread pool (4 threads)
- Semaphore throttling (4 max concurrent)
- Disk-based thumbnail cache
- WeakReference memory management

### Visual Improvements
- Black borders (2px) between thumbnails
- Fill thumbnails (object-fit: cover)
- Scroll past end (70% viewport padding)
- Fade-in animations (200ms)

### Bug Fixes
- Fixed semaphore release bug (only 10 thumbnails loading)
- Fixed white thumbnails (background loading issue)
- Fixed duplicate media items
- Fixed folder filter showing all subfolders
- Fixed memory leaks in ImageView and MediaPlayer

### New Features
- Smart folder filtering (direct children only)
- Full quality images in fullscreen mode
- Better video thumbnail generation
- Improved error handling

## 🔄 For Future Releases

### To Build Next Version:

1. Update version in `pom.xml` (3 locations)
2. Update version in `build-jar.bat`
3. Update version in `build-github-release.bat`
4. Create new release notes
5. Run `build-github-release.bat`
6. Test the build
7. Upload to GitHub

### Version Numbering:
- Major: Breaking changes (2.0.0)
- Minor: New features (1.2.0)
- Patch: Bug fixes (1.1.1)

## 📝 Files Created for This Release

- `RELEASE_NOTES_v1.1.0.md` - User-facing release notes
- `BUILD_RELEASE_v1.1.0.md` - Build instructions
- `CHANGELOG_v1.1.0.md` - Detailed technical changes
- `RELEASE_GUIDE.md` - This file

## 🐛 Troubleshooting

### Build Failed
- Delete `target` folder and try again
- Check Java version: `java -version` (should be 21+)
- Run: `mvnw.cmd clean` then `build-github-release.bat`

### ZIP Upload Fails
- Check file size (should be ~32 MB)
- Try uploading from different browser
- Check internet connection

### App Won't Launch After Download
- Extract the ZIP completely (don't run from inside ZIP)
- Check Windows Defender didn't block it
- Right-click WinGallery.exe → Properties → Unblock

## ✅ Post-Release Checklist

- [ ] Release published on GitHub
- [ ] Download link tested
- [ ] README.md updated
- [ ] Release notes published
- [ ] Old release archived (optional)
- [ ] Announcement posted (optional)

---

**Congratulations on releasing WinGallery v1.1.0!** 🎉

Your users will love the performance improvements and new features!
