package com.vito.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = VitoColors.Primary,
    onPrimary = Color.White,
    primaryContainer = VitoColors.PrimaryLight,
    onPrimaryContainer = VitoColors.TextPrimary,
    secondary = VitoColors.Secondary,
    onSecondary = Color.White,
    secondaryContainer = VitoColors.Secondary,
    onSecondaryContainer = VitoColors.TextPrimary,
    background = VitoColors.Background,
    onBackground = VitoColors.TextPrimary,
    surface = VitoColors.Surface,
    onSurface = VitoColors.TextPrimary,
    surfaceVariant = VitoColors.SurfaceVariant,
    onSurfaceVariant = VitoColors.TextSecondary,
    error = VitoColors.Error,
    onError = Color.White,
    outline = VitoColors.Divider
)

private val DarkColorScheme = darkColorScheme(
    primary = VitoColors.Primary,
    onPrimary = Color.White,
    primaryContainer = VitoColors.PrimaryDark,
    onPrimaryContainer = Color.White,
    secondary = VitoColors.Secondary,
    onSecondary = Color.White,
    background = Color(0xFF1A1A2E),
    onBackground = Color.White,
    surface = Color(0xFF2D2D44),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF3D3D55),
    onSurfaceVariant = Color(0xFFB0B0C0),
    error = VitoColors.Error,
    onError = Color.White,
    outline = Color(0xFF4D4D66)
)

/**
 * VITO Theme composable
 */
@Composable
fun VitoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = VitoTypography,
        content = content
    )
}