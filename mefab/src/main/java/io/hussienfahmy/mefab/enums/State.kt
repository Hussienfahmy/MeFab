package io.hussienfahmy.mefab.enums

/**
 * State.
 * representing the state of the whole view
 * when [EXPANDED] the [io.hussienfahmy.mefab.fabs.CenterFloatingActionButton] shows the close icon
 * and the [io.hussienfahmy.mefab.fabs.EdgeFloatingActionButton] shows on edges
 *
 * when [CLOSED] the [io.hussienfahmy.mefab.fabs.CenterFloatingActionButton] shows the Menu icon
 * and the [io.hussienfahmy.mefab.fabs.EdgeFloatingActionButton] shows on center
 */
internal enum class State {
    EXPANDED,
    CLOSED;

    fun inverse(): State = when (this) {
        EXPANDED -> CLOSED
        CLOSED -> EXPANDED
    }
}