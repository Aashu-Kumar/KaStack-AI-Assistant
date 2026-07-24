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
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import com.aashu.kai.speech.SpeechRecognizerManager

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

    val speechManager = remember {
        SpeechRecognizerManager(context)
    }

    val microphonePermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->

            if (granted) {

                speechManager.startListening(

                    onResult = { text ->
                        viewModel.setRecognizedText(text)
                    },

                    onListeningStateChanged = { listening ->
                        viewModel.setListening(listening)
                    },

                    onError = { error ->
                        android.util.Log.d("KAI_SPEECH", error)
                        viewModel.setListening(false)
                    }
                )
            }
        }

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
                isListening = uiState.isListening,
                onTextChanged = viewModel::updateMessage,
                onKeyboardClick = viewModel::toggleInput,

                onMicClick = {

                    if (
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.RECORD_AUDIO
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {

                        speechManager.startListening(

                            onResult = { text ->
                                viewModel.setRecognizedText(text)
                            },

                            onListeningStateChanged = { listening ->
                                viewModel.setListening(listening)
                            },

                            onError = { error ->
                                android.util.Log.d("KAI_SPEECH", error)
                                viewModel.setListening(false)
                            }
                        )

                    } else {

                        microphonePermissionLauncher.launch(
                            Manifest.permission.RECORD_AUDIO
                        )

                    }
                },

                onSendClick = viewModel::sendMessage
            )
        }

    ) { innerPadding ->

        ChatList(
            messages = uiState.messages,
            isTyping = uiState.isTyping,
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding
        )
    }
}