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

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aashu.kai.data.local.database.DatabaseProvider
import com.aashu.kai.viewmodel.HomeViewModel
import com.aashu.kai.viewmodel.HomeViewModelFactory

@Composable
fun HomeScreen() {

    val context = LocalContext.current

    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            DatabaseProvider
                .getDatabase(context)
                .chatMessageDao()
        )
    )

    val uiState by viewModel.uiState.collectAsState()

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
            onTextChanged = viewModel::updateMessage,
            onKeyboardClick = viewModel::toggleInput
        )
    }
}