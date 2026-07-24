package com.aashu.kai.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aashu.kai.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Query("""
        SELECT * FROM reminders
        ORDER BY reminderTime ASC
    """)
    fun getReminders(): Flow<List<ReminderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(
        reminder: ReminderEntity
    )

    @Query("DELETE FROM reminders WHERE id = :id")
    suspend fun deleteReminder(
        id: Long
    )
}