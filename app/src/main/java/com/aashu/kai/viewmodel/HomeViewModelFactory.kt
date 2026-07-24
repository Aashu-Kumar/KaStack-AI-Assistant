package com.aashu.kai.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aashu.kai.data.local.dao.ChatMessageDao

class HomeViewModelFactory(
    private val chatMessageDao: ChatMessageDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(chatMessageDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}