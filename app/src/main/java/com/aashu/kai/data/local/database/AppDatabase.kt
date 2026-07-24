package com.aashu.kai.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aashu.kai.data.local.converter.MessageMetaConverter
import com.aashu.kai.data.local.dao.ChatMessageDao
import com.aashu.kai.data.local.dao.ReminderDao
import com.aashu.kai.data.local.dao.UserProfileDao
import com.aashu.kai.data.local.entity.ChatMessageEntity
import com.aashu.kai.data.local.entity.ReminderEntity
import com.aashu.kai.data.local.entity.UserProfileEntity

@Database(
    entities = [
        UserProfileEntity::class,
        ChatMessageEntity::class,
        ReminderEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(MessageMetaConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao

    abstract fun chatMessageDao(): ChatMessageDao

    abstract fun reminderDao(): ReminderDao
}