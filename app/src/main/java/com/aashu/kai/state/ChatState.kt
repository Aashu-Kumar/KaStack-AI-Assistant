package com.aashu.kai.state

sealed interface ChatState {

    data object Idle : ChatState

    data object Typing : ChatState

    data object Validating : ChatState

    data object Processing : ChatState

    data object Responding : ChatState
}