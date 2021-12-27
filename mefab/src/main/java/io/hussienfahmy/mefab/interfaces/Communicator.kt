package io.hussienfahmy.mefab.interfaces

import io.hussienfahmy.mefab.MeFabRestricted
import io.hussienfahmy.mefab.enums.Position
import io.hussienfahmy.mefab.models.Point

/**
 * Communicator.
 * interface to communicate and send events between the [io.hussienfahmy.mefab.MovableExpandedFloatingActionButton]
 * and [io.hussienfahmy.mefab.fabs.CenterFloatingActionButton] and [io.hussienfahmy.mefab.fabs.EdgeFloatingActionButton]
 */
@MeFabRestricted
internal interface Communicator {

    /**
     * provide the [io.hussienfahmy.mefab.MovableExpandedFloatingActionButton] new Position when it moves
     * on the screen
     * @param newPosition New position of the center fab
     */
    fun onCenterFabPositionChange(newPosition: Position)

    /**
     * On container move.
     * provide the [io.hussienfahmy.mefab.MovableExpandedFloatingActionButton] with new [Point] to move to it when
     * the [io.hussienfahmy.mefab.fabs.CenterFloatingActionButton] moved
     * @param newPoint New point
     */
    fun onContainerMove(newPoint: Point)

    /**
     * On edge fab click.
     * provide the [io.hussienfahmy.mefab.MovableExpandedFloatingActionButton] with id of [io.hussienfahmy.mefab.fabs.EdgeFloatingActionButton] when it clicked
     * @param fabId Fab id
     */
    fun onEdgeFabClick(fabId: Int)
}