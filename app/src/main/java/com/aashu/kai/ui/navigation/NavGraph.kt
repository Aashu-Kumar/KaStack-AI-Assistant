package com.aashu.kai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aashu.kai.ui.StartupScreen
import com.aashu.kai.ui.home.HomeScreen
import com.aashu.kai.ui.onboarding.OnboardingScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.STARTUP
    ) {

        composable(Routes.STARTUP) {

            StartupScreen(
                onNavigateToHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.STARTUP) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate(Routes.ONBOARDING) {
                        popUpTo(Routes.STARTUP) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Routes.ONBOARDING) {

            OnboardingScreen(
                onOnboardingComplete = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.ONBOARDING) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen()
        }
    }
}