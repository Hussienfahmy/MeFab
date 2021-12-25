package com.hfahmy.mefab

/**
 * State.
 * representing the state of the whole view
 * when [EXPANDED] the [CenterFloatingActionButton] shows the close icon
 * and the [EdgeFloatingActionButton] shows on edges
 *
 * when [CLOSED] the [CenterFloatingActionButton] shows the Menu icon
 * and the [EdgeFloatingActionButton] shows on center
 */
internal enum class State {
    EXPANDED,
    CLOSED;

    fun inverse(): State = when (this) {
        EXPANDED -> CLOSED
        CLOSED -> EXPANDED
    }
}