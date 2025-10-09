package com.example.finsight.model

data class Note(
    val id: Long,
    val title: String,
    val content: String,
    val timestamp: Long,
    val createdAt: Long
)
