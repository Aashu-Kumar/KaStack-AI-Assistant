package com.aashu.kai.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun AuraCircle(
    modifier: Modifier = Modifier
) {

    val transition = rememberInfiniteTransition(label = "Aura")

    val scale by transition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1800,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Scale"
    )

    Canvas(
        modifier = modifier.fillMaxSize()
    ) {

        val radius = size.minDimension / 3f * scale

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFF6C63FF),
                    Color(0xFF3F51B5),
                    Color.Transparent
                ),
                center = Offset(size.width / 2f, size.height / 2f),
                radius = radius * 2.2f
            ),
            radius = radius * 2.2f,
            center = Offset(size.width / 2f, size.height / 2f)
        )

        drawCircle(
            color = Color.White,
            radius = radius,
            center = Offset(size.width / 2f, size.height / 2f)
        )

        drawCircle(
            color = Color(0xFF6C63FF),
            radius = radius,
            center = Offset(size.width / 2f, size.height / 2f),
            style = Stroke(width = 6f)
        )
    }
}