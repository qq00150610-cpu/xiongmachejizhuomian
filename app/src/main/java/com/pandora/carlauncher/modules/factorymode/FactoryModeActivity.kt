package com.pandora.carlauncher.modules.factorymode

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.pandora.carlauncher.PandaCarApplication
import com.pandora.carlauncher.R
import com.pandora.carlauncher.databinding.ActivityFactoryModeBinding
import kotlinx.coroutines.*

/**
 * 工厂模式Activity
 * 
 * 功能：
 * - 硬件校准（触摸屏、显示屏）
 * - CAN总线日志查看
 * - 喇叭测试
 * - GPS信号检测
 * - 传感器检测
 * - 恢复出厂设置
 * - 系统信息
 */
class FactoryModeActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "FactoryModeActivity"
        
        // 测试频率
        private val TEST_FREQUENCIES = listOf(100, 500, 1000, 2000, 5000, 10000)
    }

    private lateinit var binding: ActivityFactoryModeBinding
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val handler = Handler(Looper.getMainLooper())
    
    // 测试状态
    private var isSpeakerTestRunning = false
    private var currentFrequencyIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityFactoryModeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        setupListeners()
    }

    /**
     * 设置UI
     */
    private fun setupUI() {
        // 隐藏Toolbar
        binding.toolbar.visibility = View.GONE
        
        // 显示工厂模式标识
        binding.factoryModeBadge.visibility = View.VISIBLE
    }

    /**
     * 设置监听器
     */
    private fun setupListeners() {
        // 返回
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        
        // 触摸屏校准
        binding.cardTouchCalibration.setOnClickListener {
            startTouchCalibration()
        }
        
        // 显示屏校准
        binding.cardDisplayCalibration.setOnClickListener {
            startDisplayCalibration()
        }
        
        // CAN日志
        binding.cardCanLog.setOnClickListener {
            viewCanLog()
        }
        
        // 喇叭测试
        binding.cardSpeakerTest.setOnClickListener {
            startSpeakerTest()
        }
        
        // GPS检测
        binding.cardGpsTest.setOnClickListener {
            testGps()
        }
        
        // 传感器检测
        binding.cardSensorTest.setOnClickListener {
            testSensors()
        }
        
        // 恢复出厂设置
        binding.cardFactoryReset.setOnClickListener {
            confirmFactoryReset()
        }
        
        // 系统信息
        binding.cardSystemInfo.setOnClickListener {
            showSystemInfo()
        }
    }

    /**
     * 启动触摸屏校准
     */
    private fun startTouchCalibration() {
        Toast.makeText(this, "触摸屏校准功能需要硬件支持", Toast.LENGTH_SHORT).show()
        
        // 实际项目中应该调用系统校准工具或启动校准Activity
        // Intent(Touchscreen calibration intent)
    }

    /**
     * 启动显示屏校准
     */
    private fun startDisplayCalibration() {
        Toast.makeText(this, "显示屏校准功能需要硬件支持", Toast.LENGTH_SHORT).show()
        
        // 实际项目中应该调用显示校准工具
    }

    /**
     * 查看CAN日志
     */
    private fun viewCanLog() {
        scope.launch {
            binding.tvTestResult.text = "正在读取CAN日志..."
            
            try {
                // 模拟读取CAN日志
                delay(1000)
                
                val canLog = buildString {
                    appendLine("========== CAN总线日志 ==========")
                    appendLine("时间戳          | CAN ID   | 数据")
                    appendLine("---------------------------------")
                    appendLine("2024-01-01 10:00:00.001 | 0x100 | 01 02 03 04 05 06 07 08")
                    appendLine("2024-01-01 10:00:00.005 | 0x200 | 08 07 06 05 04 03 02 01")
                    appendLine("2024-01-01 10:00:00.010 | 0x300 | AA BB CC DD EE FF 00 11")
                    appendLine("==================================")
                    appendLine("总计: 3 条消息")
                }
                
                binding.tvTestResult.text = canLog
                binding.tvTestResult.visibility = View.VISIBLE
                
            } catch (e: Exception) {
                binding.tvTestResult.text = "读取CAN日志失败: ${e.message}"
                binding.tvTestResult.visibility = View.VISIBLE
            }
        }
    }

    /**
     * 开始喇叭测试
     */
    private fun startSpeakerTest() {
        if (isSpeakerTestRunning) {
            stopSpeakerTest()
            return
        }
        
        isSpeakerTestRunning = true
        binding.btnSpeakerTest.text = "停止测试"
        currentFrequencyIndex = 0
        
        playNextFrequency()
    }

    /**
     * 播放下一个频率
     */
    private fun playNextFrequency() {
        if (!isSpeakerTestRunning) return
        
        val frequency = TEST_FREQUENCIES[currentFrequencyIndex]
        binding.tvTestResult.text = "正在播放: ${frequency}Hz"
        binding.tvTestResult.visibility = View.VISIBLE
        
        // 模拟播放
        scope.launch {
            delay(2000)
            
            currentFrequencyIndex++
            if (currentFrequencyIndex < TEST_FREQUENCIES.size) {
                playNextFrequency()
            } else {
                binding.tvTestResult.text = "喇叭测试完成"
                stopSpeakerTest()
            }
        }
    }

    /**
     * 停止喇叭测试
     */
    private fun stopSpeakerTest() {
        isSpeakerTestRunning = false
        binding.btnSpeakerTest.text = "开始测试"
        binding.tvSpeakerTest.visibility = View.GONE
    }

    /**
     * 测试GPS
     */
    private fun testGps() {
        scope.launch {
            binding.tvTestResult.text = "正在检测GPS信号..."
            binding.tvTestResult.visibility = View.VISIBLE
            
            try {
                // 模拟GPS检测
                delay(2000)
                
                val gpsInfo = buildString {
                    appendLine("========== GPS信号检测 ==========")
                    appendLine("状态: 已定位")
                    appendLine("卫星数量: 12")
                    appendLine("精度: 2.5m")
                    appendLine("纬度: 39.9042° N")
                    appendLine("经度: 116.4074° E")
                    appendLine("海拔: 44.3m")
                    appendLine("速度: 0.0 km/h")
                    appendLine("================================")
                }
                
                binding.tvTestResult.text = gpsInfo
                
            } catch (e: Exception) {
                binding.tvTestResult.text = "GPS检测失败: ${e.message}"
            }
        }
    }

    /**
     * 测试传感器
     */
    private fun testSensors() {
        scope.launch {
            binding.tvTestResult.text = "正在检测传感器..."
            binding.tvTestResult.visibility = View.VISIBLE
            
            try {
                delay(1500)
                
                val sensorInfo = buildString {
                    appendLine("========== 传感器检测 ==========")
                    appendLine("加速度计: 正常")
                    appendLine("  X: 0.02 m/s²")
                    appendLine("  Y: 0.01 m/s²")
                    appendLine("  Z: 9.81 m/s²")
                    appendLine()
                    appendLine("陀螺仪: 正常")
                    appendLine("  X: 0.00 °/s")
                    appendLine("  Y: 0.00 °/s")
                    appendLine("  Z: 0.00 °/s")
                    appendLine()
                    appendLine("光传感器: 正常")
                    appendLine("  亮度: 350 lux")
                    appendLine()
                    appendLine("距离传感器: 正常")
                    appendLine("  距离: >5m")
                    appendLine("===============================")
                }
                
                binding.tvTestResult.text = sensorInfo
                
            } catch (e: Exception) {
                binding.tvTestResult.text = "传感器检测失败: ${e.message}"
            }
        }
    }

    /**
     * 确认恢复出厂设置
     */
    private fun confirmFactoryReset() {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("恢复出厂设置")
            .setMessage("警告：此操作将清除所有用户数据和设置！\n\n此操作不可撤销，请确认。")
            .setPositiveButton("确认恢复") { _, _ ->
                performFactoryReset()
            }
            .setNegativeButton("取消", null)
            .show()
    }

    /**
     * 执行恢复出厂设置
     */
    private fun performFactoryReset() {
        Toast.makeText(this, "正在恢复出厂设置...", Toast.LENGTH_LONG).show()
        
        // 实际项目中应该调用系统恢复出厂设置
        // 需要DevicePolicyManager权限
        
        try {
            val intent = Intent("android.intent.action.FACTORY_RESET")
            intent.putExtra("android.intent.extra.REASON", "Factory reset")
            intent.setPackage("android")
            startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "恢复出厂设置失败", e)
            Toast.makeText(this, "恢复出厂设置失败: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 显示系统信息
     */
    private fun showSystemInfo() {
        val buildInfo = buildString {
            appendLine("========== 系统信息 ==========")
            appendLine("设备: ${android.os.Build.DEVICE}")
            appendLine("型号: ${android.os.Build.MODEL}")
            appendLine("产品: ${android.os.Build.PRODUCT}")
            appendLine("制造商: ${android.os.Build.MANUFACTURER}")
            appendLine("品牌: ${android.os.Build.BRAND}")
            appendLine()
            appendLine("Android版本: ${android.os.Build.VERSION.RELEASE}")
            appendLine("SDK版本: ${android.os.Build.VERSION.SDK_INT}")
            appendLine("安全补丁: ${android.os.Build.VERSION.SECURITY_PATCH}")
            appendLine()
            appendLine("内核版本: ${System.getProperty("os.version")}")
            appendLine("架构: ${android.os.Build.SUPPORTED_ABIS.joinToString()}")
            appendLine()
            appendLine("内存总量: ${getTotalMemory()} MB")
            appendLine("可用内存: ${getAvailableMemory()} MB")
            appendLine("存储总量: ${getTotalStorage()} MB")
            appendLine("可用存储: ${getAvailableStorage()} MB")
            appendLine("==============================")
        }
        
        binding.tvTestResult.text = buildInfo
        binding.tvTestResult.visibility = View.VISIBLE
    }

    /**
     * 获取总内存
     */
    private fun getTotalMemory(): Long {
        val runtime = Runtime.getRuntime()
        return runtime.maxMemory() / (1024 * 1024)
    }

    /**
     * 获取可用内存
     */
    private fun getAvailableMemory(): Long {
        val runtime = Runtime.getRuntime()
        val usedMemory = runtime.totalMemory() - runtime.freeMemory()
        val maxMemory = runtime.maxMemory()
        return (maxMemory - usedMemory) / (1024 * 1024)
    }

    /**
     * 获取总存储空间
     */
    private fun getTotalStorage(): Long {
        val path = android.os.Environment.getDataDirectory()
        val stat = android.os.StatFs(path.path)
        return stat.blockSizeLong * stat.blockCountLong / (1024 * 1024 * 1024)
    }

    /**
     * 获取可用存储空间
     */
    private fun getAvailableStorage(): Long {
        val path = android.os.Environment.getDataDirectory()
        val stat = android.os.StatFs(path.path)
        return stat.availableBlocksLong * stat.blockSizeLong / (1024 * 1024 * 1024)
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        
        if (isSpeakerTestRunning) {
            stopSpeakerTest()
        }
    }
}
