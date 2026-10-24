package com.transforme90.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Navy = Color(0xFF0B1530)
val NavySurface = Color(0xFF132046)
val Gold = Color(0xFFD4AF37)
val OffWhite = Color(0xFFF5F1E6)
val SoftGrey = Color(0xFF9AA3B8)

private val DarkColors = darkColorScheme(
    primary = Gold, onPrimary = Navy,
    background = Navy, onBackground = OffWhite,
    surface = NavySurface, onSurface = OffWhite,
    surfaceVariant = NavySurface, onSurfaceVariant = SoftGrey,
    secondaryContainer = Gold.copy(alpha = 0.2f), onSecondaryContainer = OffWhite
)

private val LightColors = lightColorScheme(
    primary = Navy, onPrimary = OffWhite,
    background = OffWhite, onBackground = Navy,
    surface = Color.White, onSurface = Navy
)

@Composable
fun Transforme90Theme(darkTheme: Boolean = true, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}
