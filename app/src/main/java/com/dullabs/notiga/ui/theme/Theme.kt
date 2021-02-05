package com.dullabs.notiga.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
        primary = blue500,
        primaryVariant = blue400,
        secondary = blue200,
        background = blue700,
        surface = blue400,
        error = red200,
        onPrimary = white600,
        onSecondary = white600,
        onBackground = white600,
        onSurface = white600,
        onError = white600
)

private val LightColorPalette = lightColors(
        primary = blue500,
        primaryVariant = blue400,
        secondary = blue200,
        secondaryVariant = blue100,
        background = blue700,
        surface = blue400,
        error = red200,
        onPrimary = white600,
        onSecondary = white600,
        onBackground = white600,
        onSurface = white600,
        onError = white600
)

@Composable
fun NotigaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
    )
}