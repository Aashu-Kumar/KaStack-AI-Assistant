package com.aashu.kai.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatInput(
    text: String,
    isVisible: Boolean,
    onTextChanged: (String) -> Unit,
    onKeyboardClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        IconButton(
            onClick = onKeyboardClick
        ) {

            Icon(
                imageVector = Icons.Default.Keyboard,
                contentDescription = "Keyboard"
            )
        }

        AnimatedVisibility(
            visible = isVisible
        ) {

            OutlinedTextField(
                value = text,
                onValueChange = onTextChanged,
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text("Type a message")
                }
            )
        }
    }
}