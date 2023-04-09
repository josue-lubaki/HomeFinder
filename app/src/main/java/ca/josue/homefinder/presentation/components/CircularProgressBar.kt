package ca.josue.homefinder.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

/**
 * created by Josue Lubaki
 * date : 2023-04-09
 * version : 1.0.0
 */

@Composable
fun CircularProgressBar(
    visible: Boolean,
    color: Color = Color.White,
) {
    if (visible) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = color)
        }
    }
}