package com.hfahmy.mefab

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.floatingactionbutton.FloatingActionButton

@OptIn(MeFabRestricted::class)
internal class EdgeFloatingActionButton @JvmOverloads constructor(
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
}

public class OnEdgeFabClickListener(private val listener: (fabId: Int) -> Unit) {
    public fun onClick(fabId: Int): Unit = listener(fabId)
}