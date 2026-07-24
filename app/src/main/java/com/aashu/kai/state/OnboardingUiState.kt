package com.aashu.kai.state

import com.aashu.kai.model.UserProfile

data class OnboardingUiState(
    val currentPage: Int = 0,
    val userProfile: UserProfile = UserProfile(),
    val otp: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)