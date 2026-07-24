package com.aashu.kai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aashu.kai.ui.navigation.NavGraph
import com.aashu.kai.ui.theme.KAITheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            KAITheme {
                NavGraph()
            }
        }
    }
}