package com.pandora.carlauncher.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pandora.carlauncher.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * 状态栏Fragment
 * 
 * 显示系统状态信息：
 * - 当前时间
 * - 网络状态
 * - 蓝牙状态
 * - GPS状态
 * - 车辆速度（如果有）
 */
class StatusBarFragment : Fragment() {

    private lateinit var timeText: TextView
    private lateinit var dateText: TextView
    private lateinit var networkIcon: TextView
    private lateinit var bluetoothIcon: TextView
    private lateinit var gpsIcon: TextView
    
    private val handler = Handler(Looper.getMainLooper())
    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            updateTime()
            handler.postDelayed(this, 1000)
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_status_bar, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        timeText = view.findViewById(R.id.tv_time)
        dateText = view.findViewById(R.id.tv_date)
        networkIcon = view.findViewById(R.id.icon_network)
        bluetoothIcon = view.findViewById(R.id.icon_bluetooth)
        gpsIcon = view.findViewById(R.id.icon_gps)
        
        // 初始更新时间
        updateTime()
        updateStatusIcons()
    }
    
    override fun onResume() {
        super.onResume()
        // 开始定时更新
        handler.post(updateTimeRunnable)
    }
    
    override fun onPause() {
        super.onPause()
        // 停止定时更新
        handler.removeCallbacks(updateTimeRunnable)
    }
    
    /**
     * 更新时间显示
     */
    private fun updateTime() {
        val calendar = Calendar.getInstance()
        
        // 格式化时间
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateFormat = SimpleDateFormat("MM月dd日 E", Locale.getDefault())
        
        timeText.text = timeFormat.format(calendar.time)
        dateText.text = dateFormat.format(calendar.time)
    }
    
    /**
     * 更新状态图标
     */
    private fun updateStatusIcons() {
        // 实际项目中应该监听广播或检查系统状态
        // 这里使用占位图标
        networkIcon.text = "📶"
        bluetoothIcon.text = "📱"
        gpsIcon.text = "🛰️"
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(updateTimeRunnable)
    }
}
