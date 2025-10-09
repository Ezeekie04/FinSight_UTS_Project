package com.example.finsight.model

data class Asset(
    val name: String,
    val symbol: String,
    val price: Double,
    val changePercent: String,
    val trendUp: Boolean
)
