package com.example.finsight.data

import com.example.finsight.model.Asset
import com.example.finsight.model.Prediction

object DummyData {
    val assets = listOf(
        Asset("Gold", "XAU", 2325.75, "+0.34%", true),
        Asset("DXY Index", "DXY", 105.12, "-0.22%", false),
        Asset("WTI Crude", "WTI", 78.41, "+0.17%", true),
        Asset("Copper Futures", "COPPER", 3.68, "+0.08%", true),
        Asset("S&P 500", "SP500", 5124.00, "-0.10%", false)
    )

    val samplePredictions = listOf(
        Prediction("XAU", "2025-10-09", "UP", 0.68, "CRISIS"),
        Prediction("XAU", "2025-10-10", "DOWN", 0.54, "NORMAL")
    )
}
