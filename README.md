# 熊猫车机桌面 (PandaCarLauncher)

一款专为Android Automotive OS设计的车载信息娱乐系统（IVI）桌面启动器应用。

## 项目简介

熊猫车机桌面是一个基于Android Automotive OS / AOSP深度定制的Car Launcher，融合了车辆控制与信息娱乐功能，提供类似华为HiCar的优质用户体验。

## 功能特性

### 🎯 核心功能模块

1. **悬浮导航与多窗口管理**
   - 可拖拽半透明悬浮导航控件
   - 贴边吸附效果
   - SYSTEM_ALERT_WINDOW权限

2. **媒体中心**
   - 本地音乐播放
   - 蓝牙音乐播放
   - USB/SD卡媒体播放
   - 专辑封面显示
   - 播放列表管理

3. **空调控制（HVAC）**
   - 双温区温度调节
   - 风量控制（7档）
   - AC开关控制
   - 风向模式切换（面部/身体/脚部/全部）
   - 前后除霜控制

4. **智能导航**
   - 地图预览
   - 目的地搜索
   - 常用地点快捷入口
   - 实时导航信息显示

5. **应用管理**
   - 已安装应用列表
   - 应用搜索
   - 应用卸载/强制停止
   - 系统/用户应用分类

6. **文件管理**
   - 目录浏览
   - 文件分类查看
   - 复制/粘贴/删除/重命名
   - 新建文件夹

7. **智能悬浮球**
   - 悬浮球拖拽和吸附
   - 快捷操作菜单
   - 返回、主页、语音助手、截图等功能

8. **语音控制系统**
   - 语音唤醒词检测
   - 语义理解
   - 语音指令执行
   - TTS语音播报

9. **工厂模式**
   - 硬件校准
   - CAN日志查看
   - 喇叭测试
   - GPS信号检测
   - 传感器检测
   - 恢复出厂设置

### 🎨 UI/UX特性

- 华为HiCar风格设计
- 深色主题支持
- 卡片式交互
- 大圆角、毛玻璃效果
- 大字号、大点击热区
- 驾驶专注设计
- 动态壁纸轮播

## 技术架构

### 系统要求

- **最低Android版本**: Android 10 (API 29)
- **目标Android版本**: Android 14 (API 34)
- **设备要求**: Android Automotive OS 或支持Car API的设备

### 技术栈

- **开发语言**: Kotlin / Java
- **最低SDK**: 29
- **目标SDK**: 34
- **编译SDK**: 34
- **Gradle版本**: 8.2
- **Android Gradle Plugin**: 8.2.0
- **Kotlin版本**: 1.9.22

### 主要依赖

- AndroidX Core & AppCompat
- Material Design Components
- AndroidX Lifecycle (ViewModel, LiveData)
- AndroidX ViewPager2
- AndroidX RecyclerView
- Android Car Library (androidx.car.app)
- Kotlin Coroutines
- Coil (图片加载)
- Gson (JSON解析)

### 项目结构

```
com.pandora.carlauncher/
├── PandaCarApplication.kt          # 应用入口
├── core/
│   ├── launcher/                   # 启动器核心
│   ├── service/                    # 系统服务
│   ├── receiver/                   # 广播接收器
│   └── provider/                   # Content Provider
├── modules/
│   ├── hvac/                       # 空调控制模块
│   ├── media/                      # 媒体中心模块
│   ├── navigation/                 # 导航模块
│   ├── settings/                   # 设置模块
│   ├── appmanager/                 # 应用管理模块
│   ├── filemanager/                # 文件管理模块
│   ├── voiceassistant/             # 语音助手模块
│   ├── floatingball/               # 悬浮球模块
│   └── factorymode/                # 工厂模式模块
├── ui/
│   ├── activity/                   # Activity
│   ├── fragment/                   # Fragment
│   ├── adapter/                    # 适配器
│   ├── widget/                     # 自定义控件
│   └── view/                      # 视图
├── utils/                          # 工具类
├── constants/                      # 常量
└── data/                           # 数据模型
```

## 编译说明

### 环境要求

- JDK 11 或更高版本
- Android Studio Hedgehog (2023.1.1) 或更高版本
- Android SDK (API 34)
- Gradle 8.2

### 编译步骤

1. **克隆项目**
   ```bash
   git clone <repository-url>
   cd 熊猫车机桌面
   ```

2. **设置Gradle Wrapper**
   ```bash
   ./gradlew wrapper
   ```

3. **同步项目**
   在Android Studio中点击 "Sync Project with Gradle Files"

4. **编译Debug版本**
   ```bash
   ./gradlew assembleDebug
   ```

5. **编译Release版本**
   ```bash
   ./gradlew assembleRelease
   ```

6. **安装APK**
   ```bash
   ./gradlew installDebug
   ```

### 签名配置

Release版本需要签名配置。在 `app/build.gradle` 中添加：

```gradle
android {
    signingConfigs {
        release {
            storeFile file("your-keystore.jks")
            storePassword "your-password"
            keyAlias "your-alias"
            keyPassword "your-key-password"
        }
    }
    buildTypes {
        release {
            signingConfig signingConfigs.release
        }
    }
}
```

## 权限说明

### 系统级权限

```xml
<!-- 悬浮窗权限 -->
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />

<!-- 车辆权限 -->
<uses-permission android:name="android.car.permission.CONTROL_CAR_CLIMATE" />
<uses-permission android:name="android.car.permission.CONTROL_CAR_MEDIA" />
<uses-permission android:name="android.car.permission.CONTROL_CAR_DCM" />
<uses-permission android:name="android.car.permission.READ_CAR_SENSOR_SPEED" />

<!-- 存储权限 -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

### 系统签名

应用需要使用 platform 签名才能使用系统级权限：

```xml
android:sharedUserId="android.uid.system"
```

## 使用说明

### 首次启动

1. 安装应用后，应用会自动设置为默认桌面
2. 授予必要的权限（麦克风、存储等）
3. 授予悬浮窗权限以启用悬浮球功能
4. 享受完整的驾驶体验

### 工厂模式入口

1. 进入设置页面
2. 连续点击版本号5次
3. 输入密码 `123456`
4. 进入工厂模式

### 驾驶模式

- 驾驶模式开启后会自动限制某些功能
- 简化界面交互，提高驾驶安全性
- 车速超过阈值时自动触发

## 开发说明

### 添加新模块

1. 在 `modules/` 下创建新模块目录
2. 创建 Fragment 或 Activity
3. 在 `AndroidManifest.xml` 中注册
4. 添加对应的布局文件

### 添加新权限

1. 在 `AndroidManifest.xml` 中声明权限
2. 在 PermissionHelper 中添加权限检查逻辑
3. 在需要的地方请求权限

### 国际化

字符串资源位于 `app/src/main/res/values/strings.xml`，需要其他语言支持时请创建对应的 values-xx 目录。

## 注意事项

⚠️ **重要提示**

1. 本应用需要系统级签名才能使用完整的车辆控制功能
2. 某些功能需要实际的Android Automotive硬件支持
3. 驾驶时请勿进行复杂操作
4. 视频播放功能受车速信号控制

## 许可证

本项目仅供学习和研究使用。

## 联系方式

如有问题或建议，请提交Issue。

---

**© 2024 PandaCar. All Rights Reserved.**
