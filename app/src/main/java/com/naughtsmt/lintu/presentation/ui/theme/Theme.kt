package com.naughtsmt.lintu.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = LintuPrimary,
    primaryVariant = LintuPrimaryVariant,
    secondary = LintuSecondary,
    onPrimary = LintuOnPrimary,
    background = LintuBackground,
    onBackground = LintuOnBackground,
    onSecondary = LintuOnSecondary,
    secondaryVariant = LintuSecondaryVariant,
    surface = LintuSurface

)

private val LightColorPalette = lightColors(
    primary = LintuPrimary,
    primaryVariant = LintuOnPrimary,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun LintuTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
//    val colors =

    MaterialTheme(
//        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}