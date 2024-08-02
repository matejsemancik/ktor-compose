package dev.matsem.ktorcompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KtorImage(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(Color.Black)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                brush =
                    Brush.horizontalGradient(
                        0f to Color.Red,
                        1f to Color.Blue,
                    ),
                radius = size.minDimension * 0.8f,
                center = Offset.Zero,
            )
        }

        Text(
            modifier = modifier.padding(12.dp),
            text = "This image was generated using Compose Multiplatform on Ktor server.",
            fontSize = 20.sp,
            color = Color.White,
        )
    }
}
