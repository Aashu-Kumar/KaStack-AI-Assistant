package com.aashu.kai.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {

    var uiState by remember {
        mutableStateOf(HomeUiState())
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        AuraSection()

        ChatList(
            messages = uiState.messages,
            modifier = Modifier.weight(1f)
        )

        ChatInput(
            text = uiState.currentMessage,
            isVisible = uiState.isInputVisible,
            onTextChanged = {
                uiState = uiState.copy(currentMessage = it)
            },
            onKeyboardClick = {
                uiState = uiState.copy(
                    isInputVisible = !uiState.isInputVisible
                )
            }
        )
    }
}