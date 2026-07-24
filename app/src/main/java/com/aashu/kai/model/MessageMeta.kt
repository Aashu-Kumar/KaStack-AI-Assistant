package com.aashu.kai.model

data class MessageMeta(
    val timestamp: Long = System.currentTimeMillis(),
    val sender: String = "User",
    val status: String = "Sent"
)