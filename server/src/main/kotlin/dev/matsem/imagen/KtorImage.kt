package dev.matsem.imagen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun KtorImage(modifier: Modifier = Modifier) {
    Text(modifier = modifier, text = "Hello from ktor!", fontSize = 32.sp)
}
