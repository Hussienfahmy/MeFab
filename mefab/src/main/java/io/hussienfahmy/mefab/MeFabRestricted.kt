package io.hussienfahmy.mefab

/**
 * Me fab restricted.
 * as [io.hussienfahmy.mefab.models.Point] and [io.hussienfahmy.mefab.enums.Position] used in [MovableExpandedFloatingActionButton]
 * so they must be public so i need to prevent user from using them
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This is internal API for MeFab library, please don't rely on it."
)
public annotation class MeFabRestricted