package com.hfahmy.mefab

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.RelativeLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EdgeFloatingActionButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FloatingActionButton(context, attrs, defStyleAttr) {

    private val communicator
        get() = parent as Communicator

    init {
        isClickable = true
        size = SIZE_MINI
        compatElevation = 8f
        layoutParams =
            RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
            }
    }

    /**
     * Animate to.
     * Animate the [EdgeFloatingActionButton] to the [Point] passed
     * @param point Point to animate the edge fab to it
     */
    fun animateTo(point: Point) {
        animate()
            .x(point.x)
            .y(point.y)
            .setDuration(300)
            .setInterpolator(AnticipateOvershootInterpolator())
            .start()
    }

    override fun performClick(): Boolean {
        super.performClick()
        communicator.onEdgeFabClick(id)
        return true
    }

    class OnClickListener(private val listener: (fabId: Int) -> Unit) {
        fun onClick(fabId: Int) = listener(fabId)
    }
}