package com.example.finsight.util

import com.example.finsight.model.Prediction

interface MLModelInterface {
    // menerima fitur, mengembalikan Prediction
    fun predict(features: Map<String, Any>): Prediction
}
