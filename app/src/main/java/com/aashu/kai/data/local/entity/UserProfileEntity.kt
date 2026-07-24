package com.aashu.kai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(

    @PrimaryKey
    val id: Int = 1,

    val name: String,
    val age: String,
    val phone: String,
    val personalityTraits: String
)