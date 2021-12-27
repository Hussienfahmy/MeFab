package io.hussienfahmy.mefab.utils

import io.hussienfahmy.mefab.MeFabRestricted
import io.hussienfahmy.mefab.enums.Position

/**
 * File supplies the suitable positions for the [io.hussienfahmy.mefab.fabs.EdgeFloatingActionButton] based on the size of menu
 *  to make them fit nicely in the screen
 */

@OptIn(MeFabRestricted::class)
internal fun suitablePositionsForChildrenInTopLeft(fabSize: Int): List<Position> {
    return when (fabSize) {
        1 -> listOf(Position.BOTTOM_RIGHT)
        2 -> listOf(Position.CENTER_RIGHT, Position.BOTTOM_CENTER)
        3 -> listOf(Position.CENTER_RIGHT, Position.BOTTOM_RIGHT, Position.BOTTOM_CENTER)
        else -> throwNumberOfItemsItemsException(fabSize)
    }
}

@OptIn(MeFabRestricted::class)
internal fun suitablePositionsForChildrenInTopCenter(fabSize: Int): List<Position> {
    return when (fabSize) {
        1 -> listOf(Position.BOTTOM_CENTER)
        2 -> listOf(Position.BOTTOM_RIGHT, Position.BOTTOM_LEFT)
        3 -> listOf(Position.CENTER_RIGHT, Position.BOTTOM_CENTER, Position.CENTER_LEFT)
        else -> throwNumberOfItemsItemsException(fabSize)
    }
}

@OptIn(MeFabRestricted::class)
internal fun suitablePositionsForChildrenInTopRight(fabSize: Int): List<Position> {
    return when (fabSize) {
        1 -> listOf(Position.BOTTOM_LEFT)
        2 -> listOf(Position.BOTTOM_CENTER, Position.CENTER_LEFT)
        3 -> listOf(Position.BOTTOM_CENTER, Position.BOTTOM_LEFT, Position.CENTER_LEFT)
        else -> throwNumberOfItemsItemsException(fabSize)
    }
}

@OptIn(MeFabRestricted::class)
internal fun suitablePositionsForChildrenInCenterLeft(fabSize: Int): List<Position> {
    return when (fabSize) {
        1 -> listOf(Position.CENTER_RIGHT)
        2 -> listOf(Position.TOP_RIGHT, Position.BOTTOM_RIGHT)
        3 -> listOf(Position.TOP_CENTER, Position.CENTER_RIGHT, Position.BOTTOM_CENTER)
        else -> throwNumberOfItemsItemsException(fabSize)
    }
}

@OptIn(MeFabRestricted::class)
internal fun suitablePositionsForChildrenInCenter(fabSize: Int): List<Position> {
    return when (fabSize) {
        1 -> listOf(Position.TOP_CENTER)
        2 -> listOf(Position.TOP_LEFT, Position.TOP_RIGHT)
        3 -> listOf(Position.TOP_CENTER, Position.BOTTOM_RIGHT, Position.BOTTOM_LEFT)
        else -> throwNumberOfItemsItemsException(fabSize)
    }
}

@OptIn(MeFabRestricted::class)
internal fun suitablePositionsForChildrenInCenterRight(fabSize: Int): List<Position> {
    return when (fabSize) {
        1 -> listOf(Position.CENTER_LEFT)
        2 -> listOf(Position.BOTTOM_LEFT, Position.TOP_LEFT)
        3 -> listOf(Position.BOTTOM_CENTER, Position.CENTER_LEFT, Position.TOP_CENTER)
        else -> throwNumberOfItemsItemsException(fabSize)
    }
}

@OptIn(MeFabRestricted::class)
internal fun suitablePositionsForChildrenInBottomLeft(fabSize: Int): List<Position> {
    return when (fabSize) {
        1 -> listOf(Position.TOP_RIGHT)
        2 -> listOf(Position.TOP_CENTER, Position.CENTER_RIGHT)
        3 -> listOf(Position.TOP_CENTER, Position.TOP_RIGHT, Position.CENTER_RIGHT)
        else -> throwNumberOfItemsItemsException(fabSize)
    }
}

@OptIn(MeFabRestricted::class)
internal fun suitablePositionsForChildrenInBottomCenter(fabSize: Int): List<Position> {
    return when (fabSize) {
        1 -> listOf(Position.TOP_CENTER)
        2 -> listOf(Position.TOP_LEFT, Position.TOP_RIGHT)
        3 -> listOf(Position.CENTER_LEFT, Position.TOP_CENTER, Position.CENTER_RIGHT)
        else -> throwNumberOfItemsItemsException(fabSize)
    }
}

@OptIn(MeFabRestricted::class)
internal fun suitablePositionsForChildrenInBottomRight(fabSize: Int): List<Position> {
    return when (fabSize) {
        1 -> listOf(Position.TOP_LEFT)
        2 -> listOf(Position.CENTER_LEFT, Position.TOP_CENTER)
        3 -> listOf(Position.CENTER_LEFT, Position.TOP_LEFT, Position.TOP_CENTER)
        else -> throwNumberOfItemsItemsException(fabSize)
    }
}

internal fun throwNumberOfItemsItemsException(size: Int): Nothing {
    throw IllegalStateException("Max items is 3 and Min is 1, the current is $size")
}