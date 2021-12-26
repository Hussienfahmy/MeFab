package com.hfahmy.mefab

/**
 * Me fab restricted.
 * as [com.hfahmy.mefab.models.Point] and [com.hfahmy.mefab.enums.Position] used in [MovableFloatingExpandedActionButton]
 * so they must be public so i need to prevent user from using them
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This is internal API for MeFab library, please don't rely on it."
)
public annotation class MeFabRestricted