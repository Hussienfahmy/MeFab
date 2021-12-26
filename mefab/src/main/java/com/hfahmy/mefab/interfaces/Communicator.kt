package com.hfahmy.mefab.interfaces

import com.hfahmy.mefab.MeFabRestricted
import com.hfahmy.mefab.enums.Position
import com.hfahmy.mefab.models.Point

/**
 * Communicator.
 * interface to communicate and send events between the [com.hfahmy.mefab.MovableFloatingExpandedActionButton]
 * and [com.hfahmy.mefab.fabs.CenterFloatingActionButton] and [com.hfahmy.mefab.fabs.EdgeFloatingActionButton]
 */
@MeFabRestricted
internal interface Communicator {

    /**
     * provide the [com.hfahmy.mefab.MovableFloatingExpandedActionButton] new Position when it moves
     * on the screen
     * @param newPosition New position of the center fab
     */
    fun onCenterFabPositionChange(newPosition: Position)

    /**
     * On container move.
     * provide the [com.hfahmy.mefab.MovableFloatingExpandedActionButton] with new [Point] to move to it when
     * the [com.hfahmy.mefab.fabs.CenterFloatingActionButton] moved
     * @param newPoint New point
     */
    fun onContainerMove(newPoint: Point)

    /**
     * On edge fab click.
     * provide the [com.hfahmy.mefab.MovableFloatingExpandedActionButton] with id of [com.hfahmy.mefab.fabs.EdgeFloatingActionButton] when it clicked
     * @param fabId Fab id
     */
    fun onEdgeFabClick(fabId: Int)
}