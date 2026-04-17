package com.pandora.carlauncher.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager

/**
 * 首选项管理器
 * 
 * 统一管理应用配置项
 */
class PreferencesManager(context: Context) {

    companion object {
        private const val TAG = "PreferencesManager"
        
        // 配置键
        const val KEY_NIGHT_MODE = "night_mode"
        const val KEY_DRIVING_MODE = "driving_mode"
        const val KEY_AUTO_BRIGHTNESS = "auto_brightness"
        const val KEY_VOLUME = "volume"
        const val KEY_LAST_MEDIA_PATH = "last_media_path"
        const val KEY_LAST_MEDIA_POSITION = "last_media_position"
        const val KEY_FLOATING_BALL_ENABLED = "floating_ball_enabled"
        const val KEY_VOICE_WAKEUP_ENABLED = "voice_wakeup_enabled"
        const val KEY_DRIVER_TEMP = "driver_temp"
        const val KEY_PASSENGER_TEMP = "passenger_temp"
        const val KEY_FAN_SPEED = "fan_speed"
        const val KEY_AC_ENABLED = "ac_enabled"
        const val KEY_HOME_WALLPAPER_INDEX = "home_wallpaper_index"
        const val KEY_FIRST_LAUNCH = "first_launch"
    }
    
    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    /**
     * 保存布尔值
     */
    fun setBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
        Log.d(TAG, "保存配置: $key = $value")
    }

    /**
     * 获取布尔值
     */
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    /**
     * 保存整数值
     */
    fun setInt(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
        Log.d(TAG, "保存配置: $key = $value")
    }

    /**
     * 获取整数值
     */
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return prefs.getInt(key, defaultValue)
    }

    /**
     * 保存长整数值
     */
    fun setLong(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }

    /**
     * 获取长整数值
     */
    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return prefs.getLong(key, defaultValue)
    }

    /**
     * 保存字符串值
     */
    fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    /**
     * 获取字符串值
     */
    fun getString(key: String, defaultValue: String = ""): String {
        return prefs.getString(key, defaultValue) ?: defaultValue
    }

    /**
     * 保存浮点数值
     */
    fun setFloat(key: String, value: Float) {
        prefs.edit().putFloat(key, value).apply()
    }

    /**
     * 获取浮点数值
     */
    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return prefs.getFloat(key, defaultValue)
    }

    /**
     * 夜间模式设置
     */
    fun setNightMode(enabled: Boolean) {
        setBoolean(KEY_NIGHT_MODE, enabled)
    }

    /**
     * 获取夜间模式状态
     */
    fun isNightMode(): Boolean {
        return getBoolean(KEY_NIGHT_MODE, false)
    }

    /**
     * 驾驶模式设置
     */
    fun setDrivingMode(enabled: Boolean) {
        setBoolean(KEY_DRIVING_MODE, enabled)
    }

    /**
     * 获取驾驶模式状态
     */
    fun isDrivingMode(): Boolean {
        return getBoolean(KEY_DRIVING_MODE, false)
    }

    /**
     * 悬浮球开关
     */
    fun setFloatingBallEnabled(enabled: Boolean) {
        setBoolean(KEY_FLOATING_BALL_ENABLED, enabled)
    }

    /**
     * 获取悬浮球开关状态
     */
    fun isFloatingBallEnabled(): Boolean {
        return getBoolean(KEY_FLOATING_BALL_ENABLED, true)
    }

    /**
     * 语音唤醒开关
     */
    fun setVoiceWakeupEnabled(enabled: Boolean) {
        setBoolean(KEY_VOICE_WAKEUP_ENABLED, enabled)
    }

    /**
     * 获取语音唤醒开关状态
     */
    fun isVoiceWakeupEnabled(): Boolean {
        return getBoolean(KEY_VOICE_WAKEUP_ENABLED, true)
    }

    /**
     * 保存驾驶座温度
     */
    fun setDriverTemp(temp: Int) {
        setInt(KEY_DRIVER_TEMP, temp)
    }

    /**
     * 获取驾驶座温度
     */
    fun getDriverTemp(): Int {
        return getInt(KEY_DRIVER_TEMP, 240) // 默认24℃
    }

    /**
     * 保存副驾驶温度
     */
    fun setPassengerTemp(temp: Int) {
        setInt(KEY_PASSENGER_TEMP, temp)
    }

    /**
     * 获取副驾驶温度
     */
    fun getPassengerTemp(): Int {
        return getInt(KEY_PASSENGER_TEMP, 240) // 默认24℃
    }

    /**
     * 保存风量
     */
    fun setFanSpeed(speed: Int) {
        setInt(KEY_FAN_SPEED, speed)
    }

    /**
     * 获取风量
     */
    fun getFanSpeed(): Int {
        return getInt(KEY_FAN_SPEED, 3) // 默认3档
    }

    /**
     * 保存AC开关状态
     */
    fun setAcEnabled(enabled: Boolean) {
        setBoolean(KEY_AC_ENABLED, enabled)
    }

    /**
     * 获取AC开关状态
     */
    fun isAcEnabled(): Boolean {
        return getBoolean(KEY_AC_ENABLED, true)
    }

    /**
     * 保存最后播放媒体路径
     */
    fun setLastMediaPath(path: String) {
        setString(KEY_LAST_MEDIA_PATH, path)
    }

    /**
     * 获取最后播放媒体路径
     */
    fun getLastMediaPath(): String {
        return getString(KEY_LAST_MEDIA_PATH, "")
    }

    /**
     * 保存最后播放位置
     */
    fun setLastMediaPosition(position: Long) {
        setLong(KEY_LAST_MEDIA_POSITION, position)
    }

    /**
     * 获取最后播放位置
     */
    fun getLastMediaPosition(): Long {
        return getLong(KEY_LAST_MEDIA_POSITION, 0L)
    }

    /**
     * 是否为首次启动
     */
    fun isFirstLaunch(): Boolean {
        return getBoolean(KEY_FIRST_LAUNCH, true)
    }

    /**
     * 标记首次启动已完成
     */
    fun setFirstLaunchCompleted() {
        setBoolean(KEY_FIRST_LAUNCH, false)
    }

    /**
     * 清除所有配置
     */
    fun clearAll() {
        prefs.edit().clear().apply()
        Log.i(TAG, "已清除所有配置")
    }

    /**
     * 重置为默认配置
     */
    fun resetToDefaults() {
        prefs.edit().apply {
            putBoolean(KEY_NIGHT_MODE, false)
            putBoolean(KEY_DRIVING_MODE, false)
            putBoolean(KEY_AUTO_BRIGHTNESS, true)
            putInt(KEY_VOLUME, 50)
            putInt(KEY_DRIVER_TEMP, 240)
            putInt(KEY_PASSENGER_TEMP, 240)
            putInt(KEY_FAN_SPEED, 3)
            putBoolean(KEY_AC_ENABLED, true)
            putBoolean(KEY_FLOATING_BALL_ENABLED, true)
            putBoolean(KEY_VOICE_WAKEUP_ENABLED, true)
            apply()
        }
        Log.i(TAG, "已重置为默认配置")
    }
}
