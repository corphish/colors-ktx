package com.corphish.colors.ktx

import android.graphics.Color
import androidx.annotation.ColorInt
import java.lang.IllegalArgumentException

/*
 * This file contains various color related extensions.
 */


/**
 * Returns a darker shade of a color that is being represented by an Int
 * which is annotated with ColorInt.
 */
@get:ColorInt
val @receiver:ColorInt Int.darkerShade: Int
    get() = ColorUtils.getDarkenedColor(this)

/**
 * Returns a lighter shade of a color that is being represented by an Int
 * which is annotated with ColorInt.
 */
@get:ColorInt
val @receiver:ColorInt Int.lighterShade: Int
        get() = ColorUtils.getLightenedColor(this)

/**
 * Returns a darker shade of a color that is being represented by an Int
 * which is annotated with ColorInt.
 *
 * @param factor Darkness factor.
 * @return Darkened shade.
 */
@ColorInt
fun @receiver:ColorInt Int.darkenShadeBy(factor: Float): Int =
        ColorUtils.getDarkenedColor(this, factor)

/**
 * Returns a lighter shade of a color that is being represented by an Int
 * which is annotated with ColorInt.
 *
 * @param factor Lightness factor.
 * @return Lightened shade.
 */
@ColorInt
fun @receiver:ColorInt Int.lightenShadeBy(factor: Float): Int =
        ColorUtils.getLightenedColor(this, factor)

/**
 * Returns a color (black or white) which would go if a surface had a color
 * represented by this color Int.
 */
@get:ColorInt
val @receiver:ColorInt Int.onSurfaceColor: Int
    get() = if (ColorUtils.isColorDark(this)) {
        Color.WHITE
    } else {
        Color.BLACK
    }

/**
 * Returns a color based on how they are selected which would go if a surface had a color
 * represented by this color Int.
 *
 * @param colorSelection Color choices when the color is dark and light. The value that is present
 *                       in the block is a boolean value indicating whether the color is dark or not.
 * @return One of the colors returned by colorSelection depending on whether the current color is
 *         dark or light.
 */
@ColorInt
fun @receiver:ColorInt Int.onSurfaceColorBy(colorSelection: (Boolean) -> Int): Int =
    colorSelection(ColorUtils.isColorDark(this))

/**
 * Converts a hexadecimal color in form of String to its Int representation.
 * If the String does not represent a color, null is returned.
 *
 * @return Color as Int if String denotes a color, null otherwise.
 * @since 0.0.1
 */
@ColorInt
fun String.asColorInt(): Int? {
    return try {
        Color.parseColor(this)
    } catch (e: IllegalArgumentException) {
        null
    }
}

/**
 * Converts a hexadecimal color in form of String to its Int representation.
 * If the String does not represent a color, fallback color is returned.
 *
 * @param fallbackColor Fallback color if the String does not denote a color.
 * @return Color as Int if String denotes a color, fallback color otherwise.
 * @since 0.0.1
 */
@ColorInt
fun String.asColorIntOr(@ColorInt fallbackColor: Int): Int {
    return try {
        Color.parseColor(this)
    } catch (e: IllegalArgumentException) {
        fallbackColor
    }
}

/**
 * Converts a hexadecimal color in form of String to its Int representation.
 * If the String does not represent a color, black color is returned.
 *
 * @return Color as Int if String denotes a color, black color otherwise.
 * @since 0.0.1
 */
@ColorInt
fun String.asColorIntOrBlack() = this.asColorIntOr(Color.BLACK)

/**
 * Converts a hexadecimal color in form of String to its Int representation.
 * If the String does not represent a color, white color is returned.
 *
 * @return Color as Int if String denotes a color, white color otherwise.
 * @since 0.0.1
 */
@ColorInt
fun String.asColorIntOrWhite() = this.asColorIntOr(Color.WHITE)