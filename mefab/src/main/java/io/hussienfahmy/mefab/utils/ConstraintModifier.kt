package io.hussienfahmy.mefab.utils

import androidx.constraintlayout.widget.ConstraintSet
import io.hussienfahmy.mefab.MeFabRestricted
import io.hussienfahmy.mefab.enums.Position

/**
 * Clear constraints.
 * clear all constraints and set the view to WRAP_CONTENT, WRAP_CONTENT
 * @param viewId View id
 */
internal fun ConstraintSet.clearConstraints(viewId: Int) {
    clear(viewId)
    constrainWidth(viewId, ConstraintSet.WRAP_CONTENT)
    constrainHeight(viewId, ConstraintSet.WRAP_CONTENT)
}

/**
 * Set constraints default.
 * clear all constraints of the view and set the view in the center
 * @receiver [ConstraintSet]
 * @param viewId View id to default it
 * @param parentId Parent id of the [viewId]
 */
internal fun ConstraintSet.setConstraintsDefault(viewId: Int, parentId: Int) {
    clearConstraints(viewId)
    constraintCenter(viewId, parentId)
    setScaleX(viewId, 0.5f)
    setScaleY(viewId, 0.5f)
}

/**
 * Set new constraints.
 * sets the constrain based on the [position] supplied
 * @receiver [ConstraintSet]
 * @param viewId View id
 * @param parentId Parent id
 * @param position Position
 */
@OptIn(MeFabRestricted::class)
internal fun ConstraintSet.setNewConstraints(viewId: Int, parentId: Int, position: Position) {
    setScaleX(viewId, 1f)
    setScaleY(viewId, 1f)

    when (position) {
        Position.TOP_LEFT -> constraintTopLeft(viewId, parentId)
        Position.TOP_CENTER -> constraintTopCenter(viewId, parentId)
        Position.TOP_RIGHT -> constraintTopRight(viewId, parentId)
        Position.CENTER_LEFT -> constraintCenterLeft(viewId, parentId)
        Position.CENTER -> constraintCenter(viewId, parentId)
        Position.CENTER_RIGHT -> constraintCenterRight(viewId, parentId)
        Position.BOTTOM_LEFT -> constraintBottomLeft(viewId, parentId)
        Position.BOTTOM_CENTER -> constraintBottomCenter(viewId, parentId)
        Position.BOTTOM_RIGHT -> constraintBottomRight(viewId, parentId)
    }
}

/**
 * constraint the view in the top left corner of parent
 */
private fun ConstraintSet.constraintTopLeft(viewId: Int, parentId: Int) {
    connect(viewId, ConstraintSet.START, parentId, ConstraintSet.START)
    connect(viewId, ConstraintSet.TOP, parentId, ConstraintSet.TOP)
}

/**
 * constraint the view in the top center corner of parent
 */
private fun ConstraintSet.constraintTopCenter(viewId: Int, parentId: Int) {
    connect(viewId, ConstraintSet.TOP, parentId, ConstraintSet.TOP)
    connect(viewId, ConstraintSet.START, parentId, ConstraintSet.START)
    connect(viewId, ConstraintSet.END, parentId, ConstraintSet.END)
}

/**
 * constraint the view in the top right corner of parent
 */
private fun ConstraintSet.constraintTopRight(viewId: Int, parentId: Int) {
    connect(viewId, ConstraintSet.END, parentId, ConstraintSet.END)
    connect(viewId, ConstraintSet.TOP, parentId, ConstraintSet.TOP)
}

/**
 * constraint the view in the center left corner of parent
 */
private fun ConstraintSet.constraintCenterLeft(viewId: Int, parentId: Int) {
    connect(viewId, ConstraintSet.START, parentId, ConstraintSet.START)
    connect(viewId, ConstraintSet.TOP, parentId, ConstraintSet.TOP)
    connect(viewId, ConstraintSet.BOTTOM, parentId, ConstraintSet.BOTTOM)
}

/**
 * constraint the view in the center corner of parent
 */
private fun ConstraintSet.constraintCenter(viewId: Int, parentId: Int) {
    connect(viewId, ConstraintSet.TOP, parentId, ConstraintSet.TOP)
    connect(viewId, ConstraintSet.START, parentId, ConstraintSet.START)
    connect(viewId, ConstraintSet.END, parentId, ConstraintSet.END)
    connect(viewId, ConstraintSet.BOTTOM, parentId, ConstraintSet.BOTTOM)
}

/**
 * constraint the view in the center right corner of parent
 */
private fun ConstraintSet.constraintCenterRight(viewId: Int, parentId: Int) {
    connect(viewId, ConstraintSet.END, parentId, ConstraintSet.END)
    connect(viewId, ConstraintSet.TOP, parentId, ConstraintSet.TOP)
    connect(viewId, ConstraintSet.BOTTOM, parentId, ConstraintSet.BOTTOM)
}

/**
 * constraint the view in the bottom left corner of parent
 */
private fun ConstraintSet.constraintBottomLeft(viewId: Int, parentId: Int) {
    connect(viewId, ConstraintSet.START, parentId, ConstraintSet.START)
    connect(viewId, ConstraintSet.BOTTOM, parentId, ConstraintSet.BOTTOM)
}

/**
 * constraint the view in the bottom center corner of parent
 */
private fun ConstraintSet.constraintBottomCenter(viewId: Int, parentId: Int) {
    connect(viewId, ConstraintSet.BOTTOM, parentId, ConstraintSet.BOTTOM)
    connect(viewId, ConstraintSet.START, parentId, ConstraintSet.START)
    connect(viewId, ConstraintSet.END, parentId, ConstraintSet.END)
}

/**
 * constraint the view in the bottom right corner of parent
 */
private fun ConstraintSet.constraintBottomRight(viewId: Int, parentId: Int) {
    connect(viewId, ConstraintSet.END, parentId, ConstraintSet.END)
    connect(viewId, ConstraintSet.BOTTOM, parentId, ConstraintSet.BOTTOM)
}