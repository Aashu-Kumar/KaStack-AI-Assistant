package com.aashu.kai.viewmodel

import androidx.lifecycle.ViewModel
import com.aashu.kai.model.UserProfile
import com.aashu.kai.state.OnboardingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

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
                userProfile = it.userProfile.copy(
                    age = age.toIntOrNull()
                ),
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

            profile.age == null -> {
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

    private fun showError(message: String) {
        _uiState.update {
            it.copy(errorMessage = message)
        }
    }
}