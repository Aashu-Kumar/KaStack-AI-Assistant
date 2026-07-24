package com.aashu.kai.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatInput(
    text: String,
    isVisible: Boolean,
    isListening: Boolean,
    onTextChanged: (String) -> Unit,
    onKeyboardClick: () -> Unit,
    onMicClick: () -> Unit,
    onSendClick: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // Always visible microphone
            IconButton(
                onClick = onMicClick
            ) {
                Icon(
                    imageVector = if (isListening)
                        Icons.Default.MicOff
                    else
                        Icons.Default.Mic,
                    contentDescription = "Microphone"
                )
            }

            // Keyboard toggle
            IconButton(
                onClick = onKeyboardClick
            ) {
                Icon(
                    imageVector = Icons.Default.Keyboard,
                    contentDescription = "Keyboard"
                )
            }

            if (isVisible) {

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedTextField(
                        value = text,
                        onValueChange = onTextChanged,
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text("Type a message")
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(24.dp)
                    )

                    AnimatedVisibility(
                        visible = text.isNotBlank()
                    ) {

                        IconButton(
                            onClick = onSendClick
                        ) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "Send"
                            )
                        }
                    }
                }
            }
        }
    }
}