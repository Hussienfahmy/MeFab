package com.hfahmy.mefab

/**
 * Point.
 * to reduce code to call [Int.toFloat] as we need float values to animate
 * @property x
 * @property y
 * @constructor Create [Point]
 */
@MeFabRestricted
public data class Point(val x: Float, val y: Float)