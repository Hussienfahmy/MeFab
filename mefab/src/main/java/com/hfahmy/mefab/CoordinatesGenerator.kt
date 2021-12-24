package com.hfahmy.mefab

import android.view.View

/**
 * Point.
 * to reduce code to call [Int.toFloat] as we need float values to animate
 * @property x
 * @property y
 * @constructor Create [Point]
 */
data class Point(val x: Float, val y: Float)

/**
 * Coordinates generator.
 * calculate and supply a coordinates within a ground view
 *
 * @constructor Create [CoordinatesGenerator]
 *
 * @param groundWidth the groundWidth of the ground view which you want to get a coordinate within it
 * @param groundHeight the groundHeight of the ground view which you want to get a coordinate within it
 */
class CoordinatesGenerator(private val groundWidth: Int, private val groundHeight: Int) {

    /**
     * Get coordinates.
     *
     * @param position Position in the grid which you want to find a coordinate for it
     * @param view View which you want to find a coordinate for it
     * @return [Point] represent the coordinate
     */
    fun getCoordinates(position: Position, view: View): Point {
        return when (position) {
            Position.TOP_LEFT -> topLeftPosition
            Position.TOP_CENTER -> topCenterPosition(view.width)
            Position.TOP_RIGHT -> topRightPosition(view.width)
            Position.CENTER_LEFT -> centerLeftPosition(view.height)
            Position.CENTER -> centerPosition(view.width, view.height)
            Position.CENTER_RIGHT -> centerRightPosition(view.width, view.height)
            Position.BOTTOM_RIGHT -> bottomRightPosition(view.width, view.height)
            Position.BOTTOM_CENTER -> bottomCenterPosition(view.width, view.height)
            Position.BOTTOM_LEFT -> bottomLeftPosition(view.height)
        }
    }

    // ---------------------------- TOP ------------------------------------------------------------

    /**
     * top left position (0f, 0f)
     */
    private val topLeftPosition get() = Point(0f, 0f)

    /**
     * @param viewWidth View width which you want to get coordinates for it
     * @return the coordinates of the top center position in the ground view for the supplied view width
     */
    private fun topCenterPosition(viewWidth: Int) = Point((groundWidth / 2) - (viewWidth / 2).toFloat(), 0f)

    /**
     * @param viewWidth View width which you want to get coordinates for it
     * @return the coordinates of the top right position in the ground view for the supplied view width
     */
    private fun topRightPosition(viewWidth: Int) = Point((groundWidth - viewWidth).toFloat(), 0f)

    // ---------------------------- Center ---------------------------------------------------------

    /**
     * @param viewHeight View height which you want to get coordinates for it
     * @return the coordinates of the center left position in the ground view for the supplied view width
     */
    private fun centerLeftPosition(viewHeight: Int) = Point(0f, (groundHeight / 2f) - (viewHeight / 2))

    /**
     * @param viewHeight View height which you want to get coordinates for it
     * @param viewWidth View width which you want to get coordinates for it
     * @return the coordinates of the center position in the ground view for the supplied view width
     */
    private fun centerPosition(viewWidth: Int, viewHeight: Int) = Point(
        (groundWidth / 2f) - (viewWidth / 2),
        (groundHeight / 2f) - (viewHeight / 2)
    )

    /**
     * @param viewHeight View height which you want to get coordinates for it
     * @param viewWidth View width which you want to get coordinates for it
     * @return the coordinates of the center right position in the ground view for the supplied view width
     */
    private fun centerRightPosition(viewWidth: Int, viewHeight: Int) = Point(
        (groundWidth - viewWidth).toFloat(),
        (groundHeight / 2f) - (viewHeight / 2)
    )

    // ---------------------------- BOTTOM ---------------------------------------------------------

    /**
     * @param viewHeight View height which you want to get coordinates for it
     * @return the coordinates of the bottom left position in the ground view for the supplied view width
     */
    private fun bottomLeftPosition(viewHeight: Int) = Point(0f, (groundHeight - viewHeight).toFloat())

    /**
     * @param viewHeight View height which you want to get coordinates for it
     * @param viewWidth View width which you want to get coordinates for it
     * @return the coordinates of the bottom center right position in the ground view for the supplied view width
     */
    private fun bottomCenterPosition(viewWidth: Int, viewHeight: Int) = Point(
        (groundWidth / 2f) - (viewWidth / 2),
        (groundHeight - viewHeight).toFloat()
    )

    /**
     * @param viewHeight View height which you want to get coordinates for it
     * @param viewWidth View width which you want to get coordinates for it
     * @return the coordinates of the bottom right right position in the ground view for the supplied view width
     */
    private fun bottomRightPosition(viewWidth: Int, viewHeight: Int) = Point(
        (groundWidth - viewWidth).toFloat(),
        (groundHeight - viewHeight).toFloat()
    )
    
}