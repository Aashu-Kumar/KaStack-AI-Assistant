package com.aashu.kai.data.datastore

import com.aashu.kai.model.UserProfile

data class UserPreferences(
    val onboardingCompleted: Boolean = false,
    val userProfile: UserProfile = UserProfile()
)