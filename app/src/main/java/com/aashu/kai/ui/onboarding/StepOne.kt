package com.aashu.kai.ui.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun StepOne(
    onNext: () -> Unit
) {

    var showFirst by remember { mutableStateOf(false) }
    var showSecond by remember { mutableStateOf(false) }
    var showThird by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        delay(400)
        showFirst = true

        delay(700)
        showSecond = true

        delay(700)
        showThird = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome to KAI",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        AnimatedVisibility(
            visible = showFirst,
            enter = fadeIn() + slideInVertically()
        ) {
            Text("• Your personal AI companion")
        }

        Spacer(modifier = Modifier.height(12.dp))

        AnimatedVisibility(
            visible = showSecond,
            enter = fadeIn() + slideInVertically()
        ) {
            Text("• Conversations stay on your device")
        }

        Spacer(modifier = Modifier.height(12.dp))

        AnimatedVisibility(
            visible = showThird,
            enter = fadeIn() + slideInVertically()
        ) {
            Text("• Personalized according to your preferences")
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onNext
        ) {
            Text("Next")
        }
    }
}