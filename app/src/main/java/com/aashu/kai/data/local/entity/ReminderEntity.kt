package com.aashu.kai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String,

    val description: String,

    val reminderTime: Long,

    val completed: Boolean = false,

    val lastSyncedAt: Long = 0L
)