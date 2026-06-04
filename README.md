# AndroidUtils

[![](https://jitpack.io/v/HJXANDHMR/AndroidUtils.svg)](https://jitpack.io/#HJXANDHMR/AndroidUtils)
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A lightweight Android utility library providing a collection of static helper classes for common tasks such as logging, networking, file operations, screen utilities, date formatting, and more.

## Features

- **16 utility classes** covering logging, networking, file I/O, screen info, date formatting, SharedPreferences, density conversion, and more
- **Custom animated CheckBox view** with circle-fill animation
- **Min SDK 15** — compatible with Android 4.0.3+
- **AndroidX** support
- **Zero extra dependencies** — only depends on `androidx.appcompat`

## Installation

### Step 1. Add the JitPack repository to your build file

Add it in your root `build.gradle` at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2. Add the dependency

```gradle
dependencies {
    implementation 'com.github.HJXANDHMR:AndroidUtils:2.0.0'
}
```

## Utilities

| Class | Description |
|---|---|
| `AppUtil` | Get app name, versionName, and versionCode |
| `ColorsUtil` | Color manipulation helpers |
| `DateUtil` | Date parsing, formatting, and arithmetic |
| `DensityUtil` | Unit conversion between dp, px, and sp |
| `ExitActivityUtil` | Activity exit and back-press handling |
| `FileUtil` | File & directory create, delete, copy, and move |
| `HttpUtil` | HTTP GET/POST requests via `HttpURLConnection` |
| `LogUtil` | Unified logging with a toggleable debug flag |
| `NetUtil` | Network connectivity checks (WiFi / mobile) |
| `PhoneUtil` | Phone and device related utilities |
| `SDCardUtil` | SD card storage path and availability |
| `SPUtil` | Type-safe SharedPreferences wrapper |
| `ScreenUtil` | Screen dimensions, status bar height, and screenshots |
| `ShortCutUtil` | App shortcut management |
| `StringUtil` | String operations: blank/empty checks, MD5, split, encode, case conversion |
| `ToastUtil` | Unified Toast display with toggleable visibility |

## Custom Views

### CheckBoxView

An animated checkbox with a circle-fill animation powered by `ObjectAnimator`.

**XML Attributes:**

| Attribute | Type | Description |
|---|---|---|
| `size` | dimension | Checkbox size |
| `color_border` | color | Border color |
| `color_background` | color | Background fill color |

**Usage:**

```xml
<utils.view.CheckBoxView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:size="24dp"
    app:color_border="#FFFFFF"
    app:color_background="#3F51B5" />
```

```java
CheckBoxView checkBox = findViewById(R.id.checkbox);
checkBox.setChecked(true);        // animated
checkBox.setChecked(false, false); // without animation
checkBox.toggle();
```

## Usage Examples

### LogUtil

```java
// Enable/disable logging
LogUtil.isDebug = true;

// Log with default tag
LogUtil.d("debug message");
LogUtil.e("error message");

// Log with custom tag
LogUtil.i("MyTag", "info message");
```

### SPUtil

```java
// Save data
SPUtil.put(context, "username", "John");
SPUtil.put(context, "age", 25);

// Read data
String name = (String) SPUtil.get(context, "username", "");
int age = (int) SPUtil.get(context, "age", 0);

// Remove / Clear
SPUtil.remove(context, "username");
SPUtil.clear(context);
```

### DateUtil

```java
// Format current date
String now = DateUtil.getCurDateStr(DateUtil.FORMAT_YMDHMS);
// e.g. "2024-01-15 14:30:00"

// Parse string to Date
Date date = DateUtil.str2Date("2024-01-15", DateUtil.FORMAT_YMD);

// Date arithmetic
Date tomorrow = DateUtil.addDay(new Date(), 1);
```

### DensityUtil

```java
int px = DensityUtil.dp2px(context, 16f);   // dp -> px
int dp = (int) DensityUtil.px2dp(context, 48f); // px -> dp
int spPx = DensityUtil.sp2px(context, 14f);  // sp -> px
```

### HttpUtil

```java
// Async GET request
HttpUtil.doGetAsyn("https://api.example.com/data", new HttpUtil.CallBack() {
    @Override
    public void onRequestComplete(String result) {
        // handle response
    }
});

// Async POST request
HttpUtil.doPostAsyn("https://api.example.com/submit", "key=value", new HttpUtil.CallBack() {
    @Override
    public void onRequestComplete(String result) {
        // handle response
    }
});
```

### NetUtil

```java
// Check network connectivity
boolean connected = NetUtil.isConnected(context);

// Check WiFi
boolean wifi = NetUtil.isWIFI(context);

// Open network settings
NetUtil.openSetting(activity);
```

### FileUtil

```java
// Create a file
FileUtil.mkFile("/sdcard/test/file.txt", true);

// Copy a file
FileUtil.copy("/source/file.txt", "/target/file.txt", false);

// Delete a directory recursively
FileUtil.delDir("/sdcard/test", true);
```

## Requirements

- Android SDK: **API 15+** (Android 4.0.3)
- Java **8**
- AndroidX

## Build from Source

```bash
git clone https://github.com/HJXANDHMR/AndroidUtils.git
cd AndroidUtils
./gradlew build
```

## Tech Stack

| Component | Version |
|---|---|
| Android Gradle Plugin | 8.4.0 |
| Gradle | 8.6 |
| compileSdk / targetSdk | 34 |
| Java | 8 |

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.
