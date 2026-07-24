package com.aashu.kai.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aashu.kai.data.local.dao.ChatMessageDao
import com.aashu.kai.data.local.entity.ChatMessageEntity
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

    init {
        loadMessages()
    }

    private fun loadMessages() {
        viewModelScope.launch {
            chatMessageDao.getMessages(
                limit = 20,
                offset = 0
            ).collect { messages ->
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
    }

    fun toggleInput() {
        _uiState.update {
            it.copy(isInputVisible = !it.isInputVisible)
        }
    }
}
