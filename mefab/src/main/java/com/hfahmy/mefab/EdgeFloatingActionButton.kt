package com.hfahmy.mefab

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnticipateOvershootInterpolator
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