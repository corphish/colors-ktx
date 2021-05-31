package com.corphish.colors.ktx

import android.graphics.Color
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ColorInstrumentedTest {

    // Random test count
    private val _randomTestCount = 1000

    /**
     * Test the generation of random light colors and also checks
     * if they are indeed light.
     */
    @Test
    fun testLightColors() {
        var successes = 0
        val generatedColors = mutableSetOf<Int>()

        repeat(_randomTestCount) {
            val color = ColorUtils.getRandomLightColor()
            generatedColors += color

            if (ColorUtils.isColorLight(color, 0.5)) {
                successes++
            }
        }

        // Ideally we should get different colors for each iteration.
        // But since its random, you never know, but we still expect
        // a decent amount of success.
        assertTrue(generatedColors.size > (0.9 * _randomTestCount).toInt())

        // All colors must be light
        assertEquals(_randomTestCount, successes)
    }

    /**
     * Test the generation of random dark colors and also checks
     * if they are indeed dark.
     */
    @Test
    fun testDarkColors() {
        var successes = 0
        val generatedColors = mutableSetOf<Int>()

        repeat(_randomTestCount) {
            val color = ColorUtils.getRandomDarkColor()
            generatedColors += color

            if (ColorUtils.isColorDark(color)) {
                successes++
            }
        }

        // Ideally we should get different colors for each iteration.
        // But since its random, you never know, but we still expect
        // a decent amount of success.
        assertTrue(generatedColors.size > (0.9 * _randomTestCount).toInt())

        // All colors must be light
        assertEquals(_randomTestCount, successes)
    }

    /**
     * Tests the surface color property.
     */
    @Test
    fun testSurfaceColor() {
        // White background should have black text
        assertEquals(Color.BLACK, Color.WHITE.onSurfaceColor)

        // Black background should have white text
        assertEquals(Color.WHITE, Color.BLACK.onSurfaceColor)
    }
}