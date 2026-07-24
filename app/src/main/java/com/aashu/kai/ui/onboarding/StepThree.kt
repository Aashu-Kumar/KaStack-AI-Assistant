package com.aashu.kai.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aashu.kai.viewmodel.OnboardingViewModel

private val traits = listOf(
    "Friendly",
    "Creative",
    "Helpful",
    "Curious",
    "Analytical",
    "Calm",
    "Confident",
    "Organized"
)

@Composable
fun StepThree(
    viewModel: OnboardingViewModel,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Select 3 Personality Traits",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            traits.forEach { trait ->

                FilterChip(
                    selected = trait in uiState.userProfile.personalityTraits,
                    onClick = {
                        viewModel.toggleTrait(trait)
                    },
                    label = {
                        Text(trait)
                    }
                )
            }
        }

        uiState.errorMessage?.let {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        androidx.compose.foundation.layout.Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(onClick = onBack) {
                Text("Back")
            }

            Button(
                onClick = {
                    if (viewModel.validateStepThree()) {
                        onFinish()
                    }
                }
            ) {
                Text("Finish")
            }
        }
    }
}