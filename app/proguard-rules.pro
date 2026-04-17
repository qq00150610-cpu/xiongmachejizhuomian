# 熊猫车机桌面 ProGuard规则

# Kotlin
-keep class kotlin.** { *; }
-keepclassmembers class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# AndroidX
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

# Material Design
-keep class com.google.android.material.** { *; }

# Car Library
-keep class androidx.car.app.** { *; }
-keep class android.car.** { *; }

# ViewBinding
-keep class * implements androidx.viewbinding.ViewBinding {
    public static *** bind(android.view.View);
    public static *** inflate(android.view.LayoutInflater);
}

# Parcelable
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
}

# Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Enum
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.** { *; }
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# 数据类
-keep class com.pandora.carlauncher.data.model.** { *; }
-keep class com.pandora.carlauncher.modules.**.** { *; }

# 广播接收器
-keep class com.pandora.carlauncher.core.receiver.** { *; }

# 服务
-keep class com.pandora.carlauncher.core.service.** { *; }
-keep class com.pandora.carlauncher.modules.media.MediaPlayService { *; }
-keep class com.pandora.carlauncher.modules.floatingball.FloatingBallService { *; }
-keep class com.pandora.carlauncher.modules.voiceassistant.VoiceAssistantService { *; }

# 保留Activity
-keep class com.pandora.carlauncher.ui.activity.** { *; }
-keep class com.pandora.carlauncher.modules.settings.SettingsActivity { *; }
-keep class com.pandora.carlauncher.modules.factorymode.FactoryModeActivity { *; }
-keep class com.pandora.carlauncher.modules.filemanager.FileManagerActivity { *; }
-keep class com.pandora.carlauncher.modules.appmanager.AppManagerActivity { *; }
-keep class com.pandora.carlauncher.modules.navigation.NavigationSearchActivity { *; }

# Fragment
-keep class com.pandora.carlauncher.ui.fragment.** { *; }
-keep class com.pandora.carlauncher.modules.**.Fragment { *; }

# 优化
-optimizationpasses 5
-allowaccessmodification
-dontpreverify
-verbose
