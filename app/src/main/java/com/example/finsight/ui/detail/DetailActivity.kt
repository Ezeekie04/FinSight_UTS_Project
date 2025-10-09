package com.example.finsight.ui.detail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.finsight.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val symbol = intent.getStringExtra("symbol") ?: "XAU"
        val title = findViewById<TextView>(R.id.txtAssetTitle)
        title.text = symbol

        val chart = findViewById<LineChart>(R.id.lineChart)
        val entries = listOf(Entry(0f, 2300f), Entry(1f, 2315f), Entry(2f, 2325f), Entry(3f, 2310f))
        val set = LineDataSet(entries, "Price")
        chart.data = LineData(set)
        chart.invalidate()

        // dummy prediction view
        val pred = findViewById<TextView>(R.id.txtPrediction)
        pred.text = "Predicted: UP (+0.7%)  Confidence: 68%  Regime: CRISIS"
    }
}
