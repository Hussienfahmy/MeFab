package io.hussienfahmy.mefab.utils

import android.util.DisplayMetrics

/**
 * Separators.
 * defining the borders separate each square on the screen
 *      |   |   |
 *      --------- y1
 *      |   |   |
 *      --------- y2
 *      |   |   |
 *       x1   x2
 * @property borderToX1Rang
 * @property x1ToX2Range
 * @property x2ToBorderRange
 * @property borderToY1Rang
 * @property y1ToY2Range
 * @property y2ToBorderRange
 */
internal data class Separators constructor(
    val borderToX1Rang: IntRange,
    val x1ToX2Range: IntRange,
    val x2ToBorderRange: IntRange,
    val borderToY1Rang: IntRange,
    val y1ToY2Range: IntRange,
    val y2ToBorderRange: IntRange,
)

internal fun DisplayMetrics.getSeparators(): Separators {
    val y1 = heightPixels / 3
    val y2 = y1 * 2
    val x1 = widthPixels / 3
    val x2 = x1 * 2

    return Separators(
        Int.MIN_VALUE..x1,
        x1..x2,
        x2..widthPixels,
        Int.MIN_VALUE..y1,
        y1..y2,
        y2..heightPixels
    )
}