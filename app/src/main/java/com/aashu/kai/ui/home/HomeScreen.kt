package com.aashu.kai.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Scaffold
import com.aashu.kai.data.local.database.DatabaseProvider
import com.aashu.kai.viewmodel.HomeViewModel
import com.aashu.kai.viewmodel.HomeViewModelFactory

@Composable
fun HomeScreen() {

    val context = LocalContext.current

    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            DatabaseProvider.getDatabase(context).chatMessageDao()
        )
    )

    val uiState by viewModel.uiState.collectAsState()
    val chatState by viewModel.chatState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),

        topBar = {
            AuraSection(
                hasMessages = uiState.messages.isNotEmpty(),
                chatState = chatState
            )
        },

        bottomBar = {
            ChatInput(
                text = uiState.currentMessage,
                isVisible = uiState.isInputVisible,
                onTextChanged = viewModel::updateMessage,
                onKeyboardClick = viewModel::toggleInput,
                onSendClick = viewModel::sendMessage
            )
        }

    ) { innerPadding ->

        ChatList(
            messages = uiState.messages,
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding
        )
    }
}