package com.aashu.kai.viewmodel

import androidx.lifecycle.ViewModel
import com.aashu.kai.model.UserProfile
import com.aashu.kai.state.OnboardingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import com.aashu.kai.data.datastore.UserPreferences
import com.aashu.kai.data.datastore.UserPreferencesRepository
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val repository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.userPreferences.collect { preferences ->
                _uiState.update {
                    it.copy(
                        userProfile = preferences.userProfile
                    )
                }
            }
        }
    }

    fun updateName(name: String) {
        _uiState.update {
            it.copy(
                userProfile = it.userProfile.copy(name = name),
                errorMessage = null
            )
        }
    }

    fun updateAge(age: String) {
        _uiState.update {
            it.copy(
                userProfile = it.userProfile.copy(age = age),
                errorMessage = null
            )
        }
    }

    fun updatePhone(phone: String) {
        _uiState.update {
            it.copy(
                userProfile = it.userProfile.copy(phone = phone),
                errorMessage = null
            )
        }
    }

    fun updateOtp(otp: String) {
        _uiState.update {
            it.copy(
                otp = otp,
                errorMessage = null
            )
        }
    }

    fun updateTraits(traits: List<String>) {
        _uiState.update {
            it.copy(
                userProfile = it.userProfile.copy(
                    personalityTraits = traits
                ),
                errorMessage = null
            )
        }
    }

    fun toggleTrait(trait: String) {
        _uiState.update { state ->

            val selected = state.userProfile.personalityTraits.toMutableList()

            if (selected.contains(trait)) {
                selected.remove(trait)
            } else {
                if (selected.size < 3) {
                    selected.add(trait)
                }
            }

            state.copy(
                userProfile = state.userProfile.copy(
                    personalityTraits = selected
                ),
                errorMessage = null
            )
        }
    }

    fun updateCurrentPage(page: Int) {
        _uiState.update {
            it.copy(currentPage = page)
        }
    }

    fun validateStepTwo(): Boolean {

        val profile = _uiState.value.userProfile

        return when {
            profile.name.isBlank() -> {
                showError("Name cannot be empty")
                false
            }

            profile.age.isBlank() || profile.age.toIntOrNull() == null -> {
                showError("Enter a valid age")
                false
            }

            profile.phone.length != 10 -> {
                showError("Phone number must be 10 digits")
                false
            }

            _uiState.value.otp != "1234" -> {
                showError("Invalid OTP")
                false
            }

            else -> true
        }
    }

    fun validateStepThree(): Boolean {

        if (_uiState.value.userProfile.personalityTraits.size != 3) {
            showError("Select exactly 3 personality traits")
            return false
        }

        return true
    }

    fun completeOnboarding() {

        viewModelScope.launch {

            repository.saveUserPreferences(
                UserPreferences(
                    onboardingCompleted = true,
                    userProfile = _uiState.value.userProfile
                )
            )
        }
    }

    private fun showError(message: String) {
        _uiState.update {
            it.copy(errorMessage = message)
        }
    }
}