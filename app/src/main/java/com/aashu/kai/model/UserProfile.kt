package com.aashu.kai.model

data class UserProfile(
    val name: String = "",
    val age: String = "",
    val phone: String = "",
    val personalityTraits: List<String> = emptyList()
)