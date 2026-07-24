package com.aashu.kai.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.aashu.kai.data.datastore.UserPreferencesRepository
import kotlinx.coroutines.flow.first

@Composable
fun StartupScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToOnboarding: () -> Unit
) {

    val context = LocalContext.current
    val repository = UserPreferencesRepository(context)

    LaunchedEffect(Unit) {

        val preferences = repository.userPreferences.first()

        if (preferences.onboardingCompleted) {
            onNavigateToHome()
        } else {
            onNavigateToOnboarding()
        }
    }
}