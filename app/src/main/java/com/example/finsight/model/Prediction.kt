package com.example.finsight.model

data class Prediction(
    val assetSymbol: String,
    val date: String,
    val direction: String, // "UP" or "DOWN"
    val confidence: Double,
    val regime: String // e.g., "CRISIS" or "NORMAL"
)
