package com.aashu.kai.model

data class UserProfile(
    val name: String = "",
    val age: Int? = null,
    val phone: String = "",
    val personalityTraits: List<String> = emptyList()
)