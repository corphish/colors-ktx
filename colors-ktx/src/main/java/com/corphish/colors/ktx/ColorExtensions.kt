package com.corphish.colors.ktx

import androidx.annotation.ColorInt

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