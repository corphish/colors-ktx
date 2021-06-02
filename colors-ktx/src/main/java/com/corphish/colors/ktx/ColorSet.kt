package com.corphish.colors.ktx

import androidx.annotation.ColorInt

/**
 * This data class encapsulates a set of background, foreground and muted version of foreground
 * color.
 * @author Avinaba Dalal
 * @since 0.0.2
 */
data class ColorSet(
    /**
     * Background color.
     * Use this to highlight the background color of some card or layout.
     */
    @ColorInt val backgroundColor: Int,

    /**
     * Foreground color.
     * Use this to highlight the main element or text on a card or layout.
     */
    @ColorInt val foregroundColor: Int = backgroundColor.onSurfaceColor,

    /**
     * Foreground muted color.
     * Use this to highlight the not-so-important elements on a card or layout.
     */
    @ColorInt val foregroundMutedColor: Int = ColorUtils.reduceAlpha(foregroundColor, 0.2f)
) {
    companion object {

        /**
         * Generates a color set for a given color.
         *
         * @param color Color from which the set needs to be generated.
         * @return Generated color set.
         * @since 0.0.2
         */
        fun of(@ColorInt color: Int) = ColorSet(color)

        /**
         * Generates a color set for some random color.
         *
         * @return Generated color set.
         * @since 0.0.2
         */
        fun ofSomeRandomColor() = ColorSet(ColorUtils.getRandomColor())

        /**
         * Generates a color set for some random dark color.
         *
         * @return Generated color set.
         * @since 0.0.2
         */
        fun ofSomeRandomDarkColor() = ColorSet(ColorUtils.getRandomDarkColor())

        /**
         * Generates a color set for some random light color.
         *
         * @return Generated color set.
         * @since 0.0.2
         */
        fun ofSomeRandomLightColor() = ColorSet(ColorUtils.getRandomLightColor())
    }
}
