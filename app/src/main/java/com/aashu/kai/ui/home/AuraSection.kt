package com.aashu.kai.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aashu.kai.state.ChatState
import com.aashu.kai.ui.components.AuraCircle

@Composable
fun AuraSection(
    hasMessages: Boolean,
    chatState: ChatState
) {

    val auraSize by animateDpAsState(
        targetValue = when {
            !hasMessages -> 170.dp
            chatState == ChatState.Responding -> 95.dp
            chatState == ChatState.Processing -> 90.dp
            chatState == ChatState.Validating -> 88.dp
            chatState == ChatState.Typing -> 85.dp
            else -> 80.dp
        },
        animationSpec = tween(350),
        label = "Aura"
    )

    if (!hasMessages) {

        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            AuraCircle(
                modifier = Modifier.size(auraSize)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "How can I help you?",
                style = MaterialTheme.typography.titleMedium
            )
        }

    } else {

        Row(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AuraCircle(
                modifier = Modifier.size(auraSize)
            )

            Spacer(modifier = Modifier.size(12.dp))

            Column {

                Text(
                    text = "KAI",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = when (chatState) {
                        ChatState.Idle -> "Ready"
                        ChatState.Typing -> "Typing..."
                        ChatState.Validating -> "Validating..."
                        ChatState.Processing -> "Thinking..."
                        ChatState.Responding -> "Responding..."
                    },
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}