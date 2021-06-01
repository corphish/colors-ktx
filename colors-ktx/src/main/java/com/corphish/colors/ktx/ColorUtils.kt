package com.corphish.colors.ktx

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 * Utility class that provides various helpers for working with colors.
 */
object ColorUtils {

    /**
     * Gets the darkness of a given color as percentage.
     * The percentage value lies between 0 and 1.
     * More the percentage of darkness, the darker the color will be.
     *
     * @param color Color as Int.
     * @return Percentage of darkness.
     */
    private fun getDarkness(@ColorInt color: Int) =
        1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255

    /*
     * Default value to determine whether a color is dark or not.
     * If the darkness returned from the getDarkness function is greater than
     * this value, it is safe to draw a light color on top of that color.
     */
    const val COLOR_ASSERTION_DARK = 0.35

    /**
     * Checks if a given color is dark or not.
     *
     * @param color Color to check.
     * @param assertionFactor Custom percentage value that should lie between 0 and 1 to determine
     *                        color darkness for a custom use-case. This parameter is optional whose
     *                        default value is 0.35. Colors whose darkness is greater than this factor
     *                        are dark.
     * @return Boolean indicating whether the given color is dark or not.
     */
    fun isColorDark(@ColorInt color: Int, assertionFactor: Double = COLOR_ASSERTION_DARK) =
        getDarkness(color) >= assertionFactor

    /**
     * Checks if a given color is light or not.
     *
     * @param color Color to check.
     * @param assertionFactor Custom percentage value that should lie between 0 and 1 to determine
     *                        color lightness for a custom use-case. This parameter is optional whose
     *                        default value is 0.35. Colors whose darkness is lesser than this factor
     *                        are light.
     * @return Boolean indicating whether the given color is light or not.
     */
    fun isColorLight(@ColorInt color: Int, assertionFactor: Double = COLOR_ASSERTION_DARK) =
        getDarkness(color) < assertionFactor

    /**
     * Produces a darker shade of the given color.
     *
     * @param color Color whose darker shade needs to be produced.
     * @param darknessFactor Factor that determines how much darker the shade will be. This must be a value
     *                       between 0 and 1. Default value is 0.2.
     * @return Darker shade of the color.
     */
    @ColorInt
    fun getDarkenedColor(@ColorInt color: Int, darknessFactor: Float = 0.2f): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)

        hsv[2] *= (1 - darknessFactor) // value component

        return Color.HSVToColor(hsv)
    }

    /**
     * Produces a lighter shade of the given color.
     *
     * @param color Color whose lighter shade needs to be produced.
     * @param lightnessFactor Factor that determines how much lighter the shade will be. This must be a value
     *                       between 0 and 1. Default value is 0.2.
     * @return Lighter shade of the color.
     */
    @ColorInt
    fun getLightenedColor(@ColorInt color: Int, lightnessFactor: Float = 0.2f): Int {
        val factor = (1 - lightnessFactor)

        val red = ((Color.red(color) * (1 - factor) / 255 + factor) * 255).toInt()
        val green = ((Color.green(color) * (1 - factor) / 255 + factor) * 255).toInt()
        val blue = ((Color.blue(color) * (1 - factor) / 255 + factor) * 255).toInt()

        return Color.argb(Color.alpha(color), red, green, blue)
    }

    /**
     * Generates a random dark color.
     *
     * @return Random dark color.
     */
    @ColorInt
    fun getRandomDarkColor() =
        RandomColor.getRandomDarkColor()

    /**
     * Generates a random light color.
     *
     * @return Random light color.
     */
    @ColorInt
    fun getRandomLightColor(): Int {
        // We generate a random dark color and invert it.
        val darkColor = getRandomDarkColor()

        // Inversion
        val red = 255 - Color.red(darkColor)
        val green = 255 - Color.green(darkColor)
        val blue = 255 - Color.blue(darkColor)

        return Color.rgb(red, green, blue)
    }

    @ColorInt
    fun getRandomColor() =
        when ((Math.random() * 2).toInt()) {
            0 -> getRandomDarkColor()
            else -> getRandomLightColor()
        }

    /**
     * Gets the hex string representation of a color.
     *
     * @param color Color.
     * @return Hex string of the color.
     */
    fun hexStringFromInt(@ColorInt color: Int) =
        String.format("#%06X", 0xFFFFFF and color)

    /**
     * This specific class will be used to generate random colors.
     * This should be more optimised than the old method.
     */
    private object RandomColor {
        /**
         * Random color mode.
         * We are going to have 2 modes:
         * 1. One-primary
         * 2. Two-primary
         *
         * For One-primary, we are going to have one primary shade with value
         * more than 192, and other 2 shades with values less than 64. Among the 2 shades, we can
         * optionally have the values to be 0.
         *
         * For Two-primary, we are going to have 2 primary shades with value ranging from 64-128,
         * while the other one will have value less than 0.
         */
        private const val MODE_ONE_PRIMARY = 0
        private const val MODE_TWO_PRIMARY = 1

        /**
         * Method to get random dark color based on one-primary mode logic.
         *
         * @return Random color based on one-primary mode logic.
         */
        @ColorInt
        private fun getOnePrimaryColor(): Int {
            // RGB int array
            val rgb = IntArray(3)

            // Make one color possibly dominant
            val dominantIndex = (Math.random() * 3).toInt()
            rgb[dominantIndex] = 128 + (Math.random() * 64).toInt()

            // Fill out remaining colors
            for (i in 0..2) {
                if (rgb[i] == 0) {
                    val coEff = (Math.random() * 2).toInt()
                    rgb[i] = (Math.random() * 32).toInt() * coEff
                }
            }

            return Color.rgb(rgb[0], rgb[1], rgb[2])
        }

        /**
         * Method to get random dark color based on two-primary mode logic.
         *
         * @return Random color based on two-primary mode logic.
         */
        @ColorInt
        private fun getTwoPrimaryColor(): Int {
            // RGB int array.
            val rgb = IntArray(3)

            // Find the first primary shade and set color.
            val dominantIndex = (Math.random() * 3).toInt()
            rgb[dominantIndex] = 80 + (Math.random() * 48).toInt()

            // Find the first primary shade and set color.
            val secondDominantIndex = 1 + (Math.random() * 2).toInt()
            rgb[(dominantIndex + secondDominantIndex) % 3] = 80 + (Math.random() * 48).toInt()

            // Fill out remaining colors
            for (i in 0..2) {
                if (rgb[i] == 0) {
                    val coEff = (Math.random() * 2).toInt()
                    rgb[i] = 0 + (Math.random() * 32).toInt() * coEff
                }
            }

            return Color.rgb(rgb[0], rgb[1], rgb[2])
        }

        /**
         * Driver method to get random dark color.
         *
         * @return Random dark color.
         */
        @ColorInt
        fun getRandomDarkColor() =
            when ((Math.random() * 2).toInt()) {
                MODE_TWO_PRIMARY -> getTwoPrimaryColor()
                else -> getOnePrimaryColor()
            }
    }
}