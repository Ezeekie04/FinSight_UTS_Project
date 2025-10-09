package com.example.finsight.ui.dashboard

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finsight.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class AssetDetailFragment : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_asset_detail, container, false)
        val chart = v.findViewById<LineChart>(R.id.lineChart)
        val title = v.findViewById<TextView>(R.id.txtAssetTitle)

        val name = arguments?.getString("name") ?: "Unknown Asset"
        val symbol = arguments?.getString("symbol") ?: "N/A"
        title.text = "$name ($symbol)"

        // Dummy data per asset
        val entries = when (symbol) {
            "XAU" -> listOf(Entry(0f, 2320f), Entry(1f, 2310f), Entry(2f, 2330f), Entry(3f, 2340f))
            "DXY" -> listOf(Entry(0f, 104f), Entry(1f, 105f), Entry(2f, 104.5f), Entry(3f, 106f))
            "WTI" -> listOf(Entry(0f, 76f), Entry(1f, 78f), Entry(2f, 77.5f), Entry(3f, 79f))
            "COPPER" -> listOf(Entry(0f, 3.5f), Entry(1f, 3.6f), Entry(2f, 3.68f), Entry(3f, 3.7f))
            "SP500" -> listOf(Entry(0f, 5100f), Entry(1f, 5120f), Entry(2f, 5110f), Entry(3f, 5130f))
            else -> emptyList()
        }

        val dataSet = LineDataSet(entries, "$symbol Trend")
        dataSet.color = Color.parseColor("#FFD700")
        dataSet.setCircleColor(Color.parseColor("#FFD700"))
        dataSet.valueTextColor = Color.WHITE
        dataSet.setDrawCircles(true)
        dataSet.setDrawValues(false)
        dataSet.lineWidth = 2f

        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.axisLeft.textColor = Color.WHITE
        chart.axisRight.isEnabled = false
        chart.xAxis.textColor = Color.WHITE
        chart.legend.textColor = Color.WHITE
        chart.description = Description().apply { text = "" }
        chart.setTouchEnabled(true)
        chart.setPinchZoom(true)
        chart.animateX(800)
        chart.animateY(800)
        chart.invalidate()

        return v
    }
}
