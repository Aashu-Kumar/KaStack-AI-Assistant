package com.aashu.kai.viewmodel

import com.aashu.kai.state.ChatState
import kotlinx.coroutines.delay
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aashu.kai.data.local.dao.ChatMessageDao
import com.aashu.kai.data.local.entity.ChatMessageEntity
import com.aashu.kai.model.MessageMeta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.aashu.kai.ui.home.HomeUiState
import kotlinx.coroutines.flow.collect


class HomeViewModel(
    private val chatMessageDao: ChatMessageDao
) : ViewModel() {


    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _chatState = MutableStateFlow<ChatState>(ChatState.Idle)
    val chatState: StateFlow<ChatState> = _chatState.asStateFlow()



    init {
        loadMessages()
    }

    private fun loadMessages() {
        viewModelScope.launch {
            chatMessageDao.getMessages().collect { messages ->
                _uiState.update {
                    it.copy(messages = messages)
                }
            }
        }
    }

    fun updateMessage(message: String) {

        _uiState.update {
            it.copy(currentMessage = message)
        }

        _chatState.value =
            if (message.isBlank())
                ChatState.Idle
            else
                ChatState.Typing
    }

    fun toggleInput() {
        _uiState.update {
            it.copy(isInputVisible = !it.isInputVisible)
        }
    }



    fun sendMessage() {

        val text = _uiState.value.currentMessage.trim()

        if (text.isBlank()) return

        _chatState.value = ChatState.Validating

        viewModelScope.launch {

            _chatState.value = ChatState.Processing

            // Insert the user's message first
            chatMessageDao.insertMessage(
                ChatMessageEntity(
                    message = text,
                    isUser = true,
                    meta = MessageMeta()
                )
            )

            // Clear the input field
            _uiState.update {
                it.copy(currentMessage = "")
            }

            _chatState.value = ChatState.Responding

            // Simulate AI thinking
            delay(800)

            // Insert AI response
            chatMessageDao.insertMessage(
                ChatMessageEntity(
                    message = generateResponse(text),
                    isUser = false,
                    meta = MessageMeta(
                        sender = "KAI"
                    )
                )
            )

            delay(500)

            _chatState.value = ChatState.Idle
        }
    }

    private fun generateResponse(userMessage: String): String {
        return when {
            userMessage.contains("hello", ignoreCase = true) ->
                "Hello! How can I assist you today?"

            userMessage.contains("hi", ignoreCase = true) ->
                "Hi! What can I help you with?"

            userMessage.contains("name", ignoreCase = true) ->
                "I'm KAI, your AI assistant."

            userMessage.contains("help", ignoreCase = true) ->
                "I'm here to answer your questions and assist you."

            else ->
                "Thanks for your message. I'm still in offline mode, but I received: \"$userMessage\""
        }
    }
}
