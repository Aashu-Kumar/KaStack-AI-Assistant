package com.aashu.kai.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aashu.kai.data.local.entity.ChatMessageEntity

@Composable
fun ChatList(
    messages: List<ChatMessageEntity>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
    ) {

        items(messages) { message ->

            Card(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
            ) {

                Text(
                    text = message.message,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}