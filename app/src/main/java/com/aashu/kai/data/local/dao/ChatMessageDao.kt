package com.aashu.kai.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aashu.kai.data.local.entity.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatMessageDao {

    @Query("""
        SELECT * FROM chat_messages
        ORDER BY id DESC
        LIMIT :limit
        OFFSET :offset
    """)
    fun getMessages(
        limit: Int,
        offset: Int
    ): Flow<List<ChatMessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(
        message: ChatMessageEntity
    )

    @Query("DELETE FROM chat_messages")
    suspend fun clearMessages()
}