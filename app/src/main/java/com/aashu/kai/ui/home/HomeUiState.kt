package com.aashu.kai.ui.home

import com.aashu.kai.data.local.entity.ChatMessageEntity

data class HomeUiState(

    val messages: List<ChatMessageEntity> = emptyList(),

    val currentMessage: String = "",

    val isInputVisible: Boolean = false,

    val isListening: Boolean = false,

    val amplitude: Float = 0f
)