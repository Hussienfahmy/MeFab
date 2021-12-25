package com.hfahmy.mefab

/**
 * Communicator.
 * interface to communicate and send events between the [MovableFloatingExpandedActionButton]
 * and [CenterFloatingActionButton] and [EdgeFloatingActionButton]
 */
interface Communicator {

    /**
     * provide the [MovableFloatingExpandedActionButton] new Position when it moves
     * on the screen
     * @param newPosition New position of the center fab
     */
    fun onCenterFabPositionChange(newPosition: Position)

    /**
     * On container move.
     * provide the [MovableFloatingExpandedActionButton] with new [Point] to move to it when
     * the [CenterFloatingActionButton] moved
     * @param newPoint New point
     */
    fun onContainerMove(newPoint: Point)

    /**
     * On edge fab click.
     * provide the [MovableFloatingExpandedActionButton] with id of [EdgeFloatingActionButton] when it clicked
     * @param fabId Fab id
     */
    fun onEdgeFabClick(fabId: Int)
}