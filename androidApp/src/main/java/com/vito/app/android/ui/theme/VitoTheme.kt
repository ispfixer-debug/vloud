package com.vito.app.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// VITO Brand Colors
private val VitoPrimary = Color(0xFF6C5CE7)
private val VitoSecondary = Color(0xFF00CEC9)
private val VitoPrimaryLight = Color(0xFF6C5CE7)
private val VitoPrimaryDark = Color(0xFF5B4CC4)

private val LightColorScheme = lightColorScheme(
    primary = VitoPrimary,
    secondary = VitoSecondary,
    tertiary = VitoPrimaryDark,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val DarkColorScheme = darkColorScheme(
    primary = VitoPrimaryLight,
    secondary = VitoSecondary,
    tertiary = VitoPrimaryDark,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun VitoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}