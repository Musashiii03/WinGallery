# Building WinGallery v1.1.0 Release

## 📋 Pre-Build Checklist

✅ Version updated to 1.1.0 in:
- `pom.xml` (3 locations)
- `build-jar.bat`
- `build-github-release.bat`

✅ Release notes created:
- `RELEASE_NOTES_v1.1.0.md`

## 🔨 Build Steps

### Step 1: Clean Previous Builds
```cmd
mvnw.cmd clean
```

### Step 2: Build the Release
Run the automated build script:
```cmd
build-github-release.bat
```

This script will:
1. Clean previous builds
2. Compile and package the application
3. Create runtime image with jlink
4. Create portable app (no installer needed)
5. Create ZIP file for distribution

**Expected Output:**
- `target\portable\WinGallery\` - Portable app folder
- `target\WinGallery-1.1.0-Windows-Portable.zip` - ZIP for GitHub release

### Step 3: Test the Build Locally
Before uploading, test the portable app:
```cmd
cd target\portable\WinGallery
WinGallery.exe
```

**Test Checklist:**
- [ ] App launches successfully
- [ ] Add a folder with images and videos
- [ ] Thumbnails generate correctly
- [ ] Black borders visible between images
- [ ] Thumbnails fill cells (not squeezed)
- [ ] Scroll past end works
- [ ] Folder filter shows only direct children
- [ ] No duplicate media items
- [ ] Fullscreen shows full quality
- [ ] Video playback works
- [ ] Session persistence works (close and reopen)

## 📤 GitHub Release Steps

### Step 1: Create New Release on GitHub
1. Go to: https://github.com/YOUR_USERNAME/WinGallery/releases/new
2. Click "Choose a tag" and type: `v1.1.0`
3. Click "Create new tag: v1.1.0 on publish"

### Step 2: Fill Release Information
**Release Title:**
```
WinGallery v1.1.0 - Major Performance & UX Update
```

**Description:**
Copy the content from `RELEASE_NOTES_v1.1.0.md`

### Step 3: Upload Build
1. Drag and drop: `target\WinGallery-1.1.0-Windows-Portable.zip`
2. Wait for upload to complete

### Step 4: Publish Release
1. Check "Set as the latest release"
2. Click "Publish release"

## 📊 Build Artifacts

After successful build, you should have:

```
target/
├── WinGallery-1.1.0.jar                          # Shaded JAR
├── WinGallery-1.1.0-Windows-Portable.zip         # For GitHub release
├── portable/
│   └── WinGallery/
│       ├── WinGallery.exe                        # Main executable
│       ├── app/                                  # Runtime image
│       └── ...
└── app/                                          # jlink runtime image
```

## 🎯 Quick Build Command

If you just want to build without the script:
```cmd
mvnw.cmd clean package -DskipTests && mvnw.cmd javafx:jlink && mvnw.cmd jpackage:jpackage@portable
```

Then manually create ZIP:
```cmd
cd target\portable
powershell -Command "Compress-Archive -Path WinGallery -DestinationPath ..\WinGallery-1.1.0-Windows-Portable.zip -Force"
```

## 🐛 Troubleshooting

### Build Fails at jlink
**Error:** Module not found
**Solution:** Ensure Java 21+ is installed and JAVA_HOME is set correctly

### Build Fails at jpackage
**Error:** WiX Toolset not found
**Solution:** This only affects MSI installer. Portable build should still work.

### ZIP Creation Fails
**Error:** PowerShell command not found
**Solution:** Run manually:
```cmd
cd target\portable
tar -a -c -f ..\WinGallery-1.1.0-Windows-Portable.zip WinGallery
```

### App Won't Launch
**Error:** Missing DLLs or runtime errors
**Solution:** 
1. Check if `target\portable\WinGallery\app\` folder exists
2. Rebuild with: `mvnw.cmd clean javafx:jlink`
3. Then: `mvnw.cmd jpackage:jpackage@portable`

## 📝 Post-Release Checklist

After publishing the release:
- [ ] Test download link works
- [ ] Download and test the ZIP from GitHub
- [ ] Update README.md with new version number
- [ ] Update main RELEASE_NOTES.md
- [ ] Announce release (if applicable)
- [ ] Archive old release notes

## 🎉 Success!

Your WinGallery v1.1.0 release is now live!

Users can:
1. Download `WinGallery-1.1.0-Windows-Portable.zip`
2. Extract anywhere
3. Run `WinGallery.exe`
4. Enjoy the improved performance and features!

---

**Build Time:** Approximately 5-10 minutes depending on your system
**ZIP Size:** ~32 MB
**Extracted Size:** ~120 MB
