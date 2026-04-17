package com.pandora.carlauncher.modules.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.pandora.carlauncher.R
import com.pandora.carlauncher.modules.navigation.NavigationSearchActivity

/**
 * 导航Fragment
 * 
 * 功能：
 * - 显示地图预览
 * - 搜索目的地
 * - 常用地点快捷入口
 * - 实时导航信息显示
 */
class NavigationFragment : Fragment() {

    companion object {
        // 常用地点
        val commonPlaces = listOf(
            CommonPlace("家", "🏠", 39.9042, 116.4074),
            CommonPlace("公司", "🏢", 39.9142, 116.4174),
            CommonPlace("超市", "🛒", 39.9242, 116.4274),
            CommonPlace("加油站", "⛽", 39.9342, 116.4374),
            CommonPlace("停车场", "🅿️", 39.9442, 116.4474),
            CommonPlace("餐厅", "🍽️", 39.9542, 116.4574),
            CommonPlace("医院", "🏥", 39.9642, 116.4674),
            CommonPlace("银行", "🏦", 39.9742, 116.4774)
        )
    }

    // UI控件
    private lateinit var searchBar: CardView
    private lateinit var currentLocationText: TextView
    private lateinit var currentStreetText: TextView
    private lateinit var etaText: TextView
    private lateinit var distanceText: TextView
    private lateinit var trafficStatusIcon: TextView
    private lateinit var commonPlacesGrid: GridLayout
    private lateinit var startNavigationButton: Button
    private lateinit var zoomInButton: ImageButton
    private lateinit var zoomOutButton: ImageButton
    private lateinit var compassButton: ImageButton
    private lateinit var centerButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupListeners()
        setupCommonPlaces()
        updateCurrentLocation()
    }

    /**
     * 初始化视图
     */
    private fun initViews(view: View) {
        searchBar = view.findViewById(R.id.card_search)
        currentLocationText = view.findViewById(R.id.tv_current_location)
        currentStreetText = view.findViewById(R.id.tv_current_street)
        etaText = view.findViewById(R.id.tv_eta)
        distanceText = view.findViewById(R.id.tv_distance)
        trafficStatusIcon = view.findViewById(R.id.icon_traffic_status)
        commonPlacesGrid = view.findViewById(R.id.grid_common_places)
        startNavigationButton = view.findViewById(R.id.btn_start_navigation)
        zoomInButton = view.findViewById(R.id.btn_zoom_in)
        zoomOutButton = view.findViewById(R.id.btn_zoom_out)
        compassButton = view.findViewById(R.id.btn_compass)
        centerButton = view.findViewById(R.id.btn_center)
    }

    /**
     * 设置监听器
     */
    private fun setupListeners() {
        // 搜索栏点击
        searchBar.setOnClickListener {
            openSearchActivity()
        }

        // 开始导航按钮
        startNavigationButton.setOnClickListener {
            startNavigation()
        }

        // 缩放按钮
        zoomInButton.setOnClickListener {
            zoomIn()
        }

        zoomOutButton.setOnClickListener {
            zoomOut()
        }

        // 指南针按钮
        compassButton.setOnClickListener {
            resetMapRotation()
        }

        // 居中按钮
        centerButton.setOnClickListener {
            centerOnCurrentLocation()
        }
    }

    /**
     * 设置常用地点网格
     */
    private fun setupCommonPlaces() {
        commonPlacesGrid.removeAllViews()

        commonPlaces.forEach { place ->
            val itemView = layoutInflater.inflate(
                R.layout.item_common_place,
                commonPlacesGrid,
                false
            )

            itemView.findViewById<TextView>(R.id.tv_icon).text = place.icon
            itemView.findViewById<TextView>(R.id.tv_name).text = place.name

            itemView.setOnClickListener {
                navigateTo(place)
            }

            commonPlacesGrid.addView(itemView)
        }
    }

    /**
     * 更新当前位置信息
     */
    private fun updateCurrentLocation() {
        // 模拟当前位置
        currentLocationText.text = "北京市朝阳区"
        currentStreetText.text = "东直门外大街"
        etaText.text = "--"
        distanceText.text = "--"
        trafficStatusIcon.text = "🟢"
    }

    /**
     * 打开搜索页面
     */
    private fun openSearchActivity() {
        val intent = Intent(requireContext(), NavigationSearchActivity::class.java)
        startActivity(intent)
    }

    /**
     * 开始导航
     */
    private fun startNavigation() {
        Toast.makeText(context, "开始导航", Toast.LENGTH_SHORT).show()
        // 实际项目中会调用地图SDK开始导航
    }

    /**
     * 导航到指定地点
     */
    private fun navigateTo(place: CommonPlace) {
        Toast.makeText(context, "正在规划到${place.name}的路线...", Toast.LENGTH_SHORT).show()
        // 实际项目中会调用地图SDK
    }

    /**
     * 放大地图
     */
    private fun zoomIn() {
        Toast.makeText(context, "放大地图", Toast.LENGTH_SHORT).show()
    }

    /**
     * 缩小地图
     */
    private fun zoomOut() {
        Toast.makeText(context, "缩小地图", Toast.LENGTH_SHORT).show()
    }

    /**
     * 重置地图旋转
     */
    private fun resetMapRotation() {
        compassButton.rotation = 0f
        Toast.makeText(context, "地图已居北", Toast.LENGTH_SHORT).show()
    }

    /**
     * 居中到当前位置
     */
    private fun centerOnCurrentLocation() {
        Toast.makeText(context, "定位到当前位置", Toast.LENGTH_SHORT).show()
    }
}

/**
 * 常用地点数据类
 */
data class CommonPlace(
    val name: String,
    val icon: String,
    val latitude: Double,
    val longitude: Double
)
