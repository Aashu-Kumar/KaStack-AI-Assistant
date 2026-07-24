package com.aashu.kai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aashu.kai.model.MessageMeta

@Entity(tableName = "chat_messages")
data class ChatMessageEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val message: String,

    val isUser: Boolean,

    val meta: MessageMeta
)