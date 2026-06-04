# AGENTS.md — AndroidUtils Project Guide for AI Agents

## Project Overview

AndroidUtils is a lightweight Android utility library providing a collection of static helper classes for common tasks such as logging, networking, file operations, screen utilities, date formatting, and more. The library is distributed via **JitPack** under the group `com.github.HJXANDHMR`.

This is a **single-module** Android Gradle project. The only module is `app/`, which is configured as an **Android library** (not an application).

## Tech Stack

| Component               | Version / Detail              |
| ----------------------- | ----------------------------- |
| Language                | Java 8 (`sourceCompatibility` / `targetCompatibility` = `VERSION_1_8`) |
| Android Gradle Plugin   | 8.4.0                         |
| Gradle                  | 8.6 (wrapper, binary dist)    |
| compileSdk              | 34                            |
| targetSdk               | 34                            |
| minSdk                  | 15                            |
| AndroidX                | `androidx.appcompat:appcompat:1.6.1` |
| Test framework          | JUnit 4.13.2                  |
| Publishing              | `maven-publish` plugin (JitPack) |

## Project Structure

```
AndroidUtils-gh/
├── app/                          # Single library module
│   ├── build.gradle              # Module-level build config (library + publishing)
│   ├── proguard-rules.pro
│   └── src/
│       ├── androidTest/          # Instrumented tests
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/utils/       # All utility classes live here
│           │   ├── view/         # Custom Android views
│           │   │   └── CheckBoxView.java
│           │   ├── AppUtil.java
│           │   ├── ColorsUtil.java
│           │   ├── DateUtil.java
│           │   ├── DensityUtil.java
│           │   ├── ExitActivityUtil.java
│           │   ├── FileUtil.java
│           │   ├── HttpUtil.java
│           │   ├── LogUtil.java
│           │   ├── NetUtil.java
│           │   ├── PhoneUtil.java
│           │   ├── SDCardUtil.java
│           │   ├── SPUtil.java
│           │   ├── ScreenUtil.java
│           │   ├── ShortCutUtil.java
│           │   ├── StringUtil.java
│           │   └── ToastUtil.java
│           └── res/
│               ├── layout/
│               ├── menu/
│               ├── mipmap-*/
│               ├── values/
│               │   ├── attrs.xml     # Custom view attributes
│               │   ├── dimens.xml
│               │   ├── strings.xml
│               │   └── styles.xml
│               └── values-w820dp/
├── build.gradle                  # Root build file (AGP plugin declaration)
├── settings.gradle               # Plugin management, repositories, module includes
├── gradle.properties             # JVM args, AndroidX flag
└── gradle/wrapper/               # Gradle wrapper (8.6)
```

## Build Configuration

### Key Files

- **Root `build.gradle`**: Declares `com.android.library` plugin version `8.4.0` (applied to subprojects).
- **`app/build.gradle`**: Module config — namespace `com.example.hjx.androidutils`, SDK levels, Java 8 compatibility, `maven-publish` setup.
- **`settings.gradle`**: Repositories (`google()`, `mavenCentral()`) with `FAIL_ON_PROJECT_REPOS` mode. Includes `:app`.
- **`gradle.properties`**: `android.useAndroidX=true`, JVM heap 2048 MB.

### Publishing (JitPack)

The library uses the `maven-publish` plugin with AGP 8+ `singleVariant('release')` and `withSourcesJar()`. Publication is configured in `afterEvaluate`:

- **groupId**: `com.github.HJXANDHMR`
- **artifactId**: `AndroidUtils`
- **version**: `1.0`

Consumers add the JitPack repository and depend on:
```
implementation 'com.github.HJXANDHMR:AndroidUtils:1.0'
```

### Build Commands

```bash
# Build the library
./gradlew build

# Run unit tests
./gradlew test

# Generate release publication (local)
./gradlew publishReleasePublicationToMavenLocal
```

## Source Code Architecture

All source code resides in the **`utils`** package (no reverse-domain prefix).

### Utility Classes

| Class              | Purpose                                                |
| ------------------ | ------------------------------------------------------ |
| `AppUtil`          | App metadata: name, versionName, versionCode           |
| `ColorsUtil`       | Color manipulation helpers                             |
| `DateUtil`         | Date parsing, formatting, arithmetic (Calendar-based)  |
| `DensityUtil`      | Unit conversion: dp/px/sp                              |
| `ExitActivityUtil` | Activity exit / back-press handling                    |
| `FileUtil`         | File & directory CRUD: create, delete, copy, move      |
| `HttpUtil`         | HTTP GET/POST via `HttpURLConnection` (async variants) |
| `LogUtil`          | Unified logging with toggleable debug flag             |
| `NetUtil`          | Network connectivity checks (WiFi, mobile)             |
| `PhoneUtil`        | Phone/device related utilities                         |
| `SDCardUtil`       | SD card storage path and availability                  |
| `SPUtil`           | SharedPreferences wrapper with type-safe get/put       |
| `ScreenUtil`       | Screen dimensions, status bar height, screenshots      |
| `ShortCutUtil`     | App shortcut management                                |
| `StringUtil`       | String operations: blank/empty checks, MD5, split, encode, case conversion |
| `ToastUtil`        | Unified Toast display with toggleable visibility       |

### Custom Views (`utils.view`)

| Class          | Purpose                                              |
| -------------- | ---------------------------------------------------- |
| `CheckBoxView` | Animated custom checkbox with circle fill animation. Implements `Checkable`. Uses `ObjectAnimator` for progress animation. |

Custom view attributes are declared in `res/values/attrs.xml`:
- `size` (dimension) — checkbox size
- `color_border` (color) — border color
- `color_background` (color) — fill color

### Permissions (AndroidManifest.xml)

- `android.permission.INTERNET`
- `android.permission.ACCESS_NETWORK_STATE`

## Code Conventions

### Design Patterns

- **Static utility pattern**: All utility classes expose only `static` methods and have a **private constructor** that throws `UnsupportedOperationException("cannot be instantiated")`.
- **No instance state**: Utility classes are stateless. Exceptions: `LogUtil.isDebug` and `ToastUtil.isShow` are public static flags for runtime toggling.

### Naming

- Package: `utils` (flat, no reverse-domain prefix like `com.example`)
- Custom views: `utils.view` sub-package
- Class names: `<Feature>Util` (e.g., `FileUtil`, `DateUtil`)
- Methods: camelCase, verb-prefixed (`getScreenWidth`, `isConnected`, `doGet`)

### Documentation

- Javadoc comments are written in **Chinese** throughout the codebase.
- New code should follow this convention for consistency.

### Java Version

- Source and target compatibility: **Java 8** (`VERSION_1_8`).
- No Kotlin in the project. All code is pure Java.

## Dependencies

| Dependency                            | Scope           |
| ------------------------------------- | --------------- |
| `androidx.appcompat:appcompat:1.6.1`  | implementation  |
| `junit:junit:4.13.2`                  | testImplementation |
| Local JARs (`libs/*.jar`)             | implementation  |

The project has minimal dependencies by design — it is a self-contained utility library.

## Development Guidelines

### Adding a New Utility Class

1. Create a new Java file in `app/src/main/java/utils/` named `<Feature>Util.java`.
2. Add a private constructor throwing `UnsupportedOperationException`.
3. Implement all methods as `static`.
4. Add Chinese Javadoc comments describing the class and each public method.

### Adding a New Custom View

1. Place the view class in `app/src/main/java/utils/view/`.
2. Declare custom attributes in `app/src/main/res/values/attrs.xml`.
3. Follow the standard three-constructor pattern: `(Context)`, `(Context, AttributeSet)`, `(Context, AttributeSet, int)`.

### Testing

- Unit tests go in `app/src/test/` (currently none exist — JUnit 4 is available).
- Instrumented tests go in `app/src/androidTest/`.
- Run tests with `./gradlew test` (unit) or `./gradlew connectedCheck` (instrumented).

### Important Notes

- **Do not change the Gradle or AGP version** — the project is locked to Gradle 8.6 + AGP 8.4.0.
- **AGP 8+ requires `singleVariant('release')`** in the `publishing` block for library publication. This is already configured in `app/build.gradle`.
- The project uses **AndroidX** (`android.useAndroidX=true`). Do not introduce legacy support library references.
- `jcenter()` is **not used** — repositories are `google()` and `mavenCentral()` only.
