package com.corphish.colors.ktx.compose

import androidx.annotation.ColorInt
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.corphish.colors.ktx.ColorUtils
import com.corphish.colors.ktx.darkerShade
import com.corphish.colors.ktx.lighterShade

/**
 * This class provides color palette builders that can be used in Compose theme.
 */
class ColorPalette(
    /**
     * Light color palette.
     */
    val lightColorPalette: Colors,

    /**
     * Dark color palette.
     */
    val darkColorPalette: Colors,
) {

    /**
     * Gets the color palette based on dark theme status.
     *
     * @param darkTheme Dark theme status.
     * @return Palette.
     */
    fun get(darkTheme: Boolean) =
        if (darkTheme) darkColorPalette else lightColorPalette

    companion object {
        /**
         * Generates a color palette for given a given primary and secondary color.
         *
         * @param primary Primary color.
         * @param secondary Secondary color.
         * @param adjustColors If true, it adjusts the colors based on the theme before generating the
         *                     palette. For example, if the primary or secondary color that is passed
         *                     is dark, it may not be visible properly in dark theme, so the color
         *                     will be lightened and then the palette will be generated. This is an
         *                     optional parameter whose default value is true. If you intend to set
         *                     this parameter as false, consider tweaking the "defaultDarkBase"
         *                     parameter accordingly.
         * @param defaultDarkBase Assumes that the colors passed are meant for dark theme. This is an
         *                        optional parameter whose default value is true.
         * @return Color palette.
         */
        fun generate(
            @ColorInt primary: Int, @ColorInt secondary: Int,
            adjustColors: Boolean = true, defaultDarkBase: Boolean = true
        ): ColorPalette {
            // Colors
            @ColorInt val primaryColor: Int
            @ColorInt val secondaryColor: Int

            // Palettes
            val lightPalette: Colors
            val darkPalette: Colors

            if (adjustColors) {
                if (defaultDarkBase) {
                    // Generate dark palette first
                    // But check if adjustments required
                    primaryColor = if (ColorUtils.isColorDark(primary)) {
                        primary.lighterShade
                    } else {
                        primary
                    }

                    secondaryColor = if (ColorUtils.isColorDark(secondary)) {
                        secondary.lighterShade
                    } else {
                        secondary
                    }

                    // For dark palette, we use the determined colors directly.
                    darkPalette = darkColors(
                        primary = Color(primaryColor),
                        secondary = Color(secondaryColor),
                        primaryVariant = Color(primaryColor.darkerShade),
                    )

                    // For lighter shade, we use darker versions.
                    lightPalette = lightColors(
                        primary = Color(primaryColor.darkerShade),
                        secondary = Color(secondaryColor.darkerShade),
                        primaryVariant = Color(primaryColor.darkerShade.darkerShade)
                    )
                } else {
                    primaryColor = if (!ColorUtils.isColorDark(primary)) {
                        primary.darkerShade
                    } else {
                        primary
                    }

                    secondaryColor = if (ColorUtils.isColorDark(secondary)) {
                        secondary.darkerShade
                    } else {
                        secondary
                    }

                    // For dark palette, we use the determined colors directly.
                    darkPalette = darkColors(
                        primary = Color(primaryColor.lighterShade),
                        secondary = Color(secondaryColor.lighterShade),
                        primaryVariant = Color(primaryColor),
                    )

                    // For lighter shade, we use darker versions.
                    lightPalette = lightColors(
                        primary = Color(primaryColor),
                        secondary = Color(secondaryColor),
                        primaryVariant = Color(primaryColor.darkerShade)
                    )
                }
            } else {
                if (defaultDarkBase) {
                    primaryColor = primary
                    secondaryColor = secondary

                    // For dark palette, we use the determined colors directly.
                    darkPalette = darkColors(
                        primary = Color(primaryColor),
                        secondary = Color(secondaryColor),
                        primaryVariant = Color(primaryColor.darkerShade),
                    )

                    // For lighter shade, we use darker versions.
                    lightPalette = lightColors(
                        primary = Color(primaryColor.darkerShade),
                        secondary = Color(secondaryColor.darkerShade),
                        primaryVariant = Color(primaryColor.darkerShade.darkerShade)
                    )
                } else {
                    primaryColor = primary
                    secondaryColor = secondary

                    // For dark palette, we use the lighter shades.
                    darkPalette = darkColors(
                        primary = Color(primaryColor.lighterShade),
                        secondary = Color(secondaryColor.lighterShade),
                        primaryVariant = Color(primaryColor),
                    )

                    // For light palette, we use determined colors.
                    lightPalette = lightColors(
                        primary = Color(primaryColor),
                        secondary = Color(secondaryColor),
                        primaryVariant = Color(primaryColor.darkerShade)
                    )
                }
            }

            return ColorPalette(
                lightColorPalette = lightPalette,
                darkColorPalette = darkPalette
            )
        }

        /**
         * Generates a random palette.
         *
         * @return Random color palette.
         */
        fun generateRandomPalette() =
            generate(
                primary = ColorUtils.getRandomDarkColor(),
                secondary = ColorUtils.getRandomDarkColor(),
            )
    }
}