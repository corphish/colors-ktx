package com.corphish.colorfeast.ui.theme

import android.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.corphish.colors.ktx.compose.ColorPalette

@Composable
fun ColorFeastTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = ColorPalette.generate(
        primary = Color.parseColor("#009933"),
        secondary = Color.parseColor("#2132cf")
    ).get(darkTheme = darkTheme)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}