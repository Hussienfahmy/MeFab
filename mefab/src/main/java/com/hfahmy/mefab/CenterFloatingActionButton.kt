package com.hfahmy.mefab

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class CenterFloatingActionButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FloatingActionButton(context, attrs, defStyleAttr) {

    private var downRawX = 0f
    private var downRawY = 0f
    private var dX = 0f
    private var dY = 0f
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop
    private val separators = resources.displayMetrics.getSeparators()
    private var state = State.CLOSED

    private val communicator
        get() = parent as Communicator

    private val motionLayout
        get() = this.parent as MotionLayout

    init {
        id = R.id.center_fab
        isClickable = true
        compatElevation = 9f
        layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        // set animated drawable
        setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.menu_to_close_anim
            )
        )
    }

    /**
     * performClick() called in [onTouchUp]
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> onTouchStart(motionEvent)
            MotionEvent.ACTION_MOVE -> onTouchMove(motionEvent)
            MotionEvent.ACTION_UP -> onTouchUp(motionEvent)
        }
        return true
    }

    private fun onTouchUp(motionEvent: MotionEvent) {
        val upRawX = motionEvent.rawX
        val upRawY = motionEvent.rawY
        val upDX = upRawX - downRawX
        val upDY = upRawY - downRawY
        if (abs(upDX) < touchTolerance && abs(upDY) < touchTolerance) {
            // A click
            performClick()
        }
    }

    private fun onTouchMove(motionEvent: MotionEvent) {
        val parentLayoutParams = motionLayout.layoutParams as ViewGroup.MarginLayoutParams
        val screenView = motionLayout.parent as View
        val screenWidth = screenView.width
        val screenHeight = screenView.height
        var newX = motionEvent.rawX + dX
        newX = max(
            parentLayoutParams.leftMargin.toFloat() - width,
            newX
        ) // Prevent the FAB pass the left hand side of the parent
        newX = min(
            (screenWidth - (motionLayout.width / 2 + width / 2) - parentLayoutParams.rightMargin).toFloat(),
            newX
        ) // Prevent the FAB pass the right hand side of the parent
        var newY = motionEvent.rawY + dY
        newY = max(
            parentLayoutParams.topMargin.toFloat() - height,
            newY
        ) // Prevent the FAB pass the top of the parent
        newY = min(
            (screenHeight - (motionLayout.height / 2 + height / 2) - parentLayoutParams.bottomMargin).toFloat(),
            newY
        ) // Prevent the FAB pass the bottom of the parent
        communicator.onContainerMove(Point(newX, newY))
        communicator.onCenterFabPositionChange(getCenterFabPositionOnScreen())
    }

    private fun onTouchStart(motionEvent: MotionEvent) {
        downRawX = motionEvent.rawX
        downRawY = motionEvent.rawY
        dX = motionLayout.x - downRawX
        dY = motionLayout.y - downRawY
    }

    override fun performClick(): Boolean {
        super.performClick()
        inverseState()
        return true
    }

    fun inverseState() {
        state = state.inverse()
        when (state) {
            State.EXPANDED -> animateIconToCloseIcon()
            State.CLOSED -> animateIconToMenuIcon()
        }
        communicator.onCenterFabPositionChange(getCenterFabPositionOnScreen())
    }

    private fun animateIconToMenuIcon() {
        setImageDrawable(ContextCompat.getDrawable(context, R.drawable.close_to_menu_anim))
        val animatedDrawable = drawable as AnimatedVectorDrawable
        animatedDrawable.start()
    }

    private fun animateIconToCloseIcon() {
        setImageDrawable(ContextCompat.getDrawable(context, R.drawable.menu_to_close_anim))
        val animatedDrawable = drawable as AnimatedVectorDrawable
        animatedDrawable.start()
    }

    private fun getCenterFabPositionOnScreen(): Position {
        // as the fab occur in the middle of the parent
        val x = (motionLayout.x + motionLayout.width / 2).toInt()
        val y = (motionLayout.y + motionLayout.height / 2).toInt()

        return when {
            y in separators.borderToY1Rang && x in separators.borderToX1Rang -> Position.TOP_LEFT
            y in separators.borderToY1Rang && x in separators.x1ToX2Range -> Position.TOP_CENTER
            y in separators.borderToY1Rang && x in separators.x2ToBorderRange -> Position.TOP_RIGHT

            y in separators.y1ToY2Range && x in separators.borderToX1Rang -> Position.CENTER_LEFT
            y in separators.y1ToY2Range && x in separators.x1ToX2Range -> Position.CENTER
            y in separators.y1ToY2Range && x in separators.x2ToBorderRange -> Position.CENTER_RIGHT

            y in separators.y2ToBorderRange && x in separators.borderToX1Rang -> Position.BOTTOM_LEFT
            y in separators.y2ToBorderRange && x in separators.x1ToX2Range -> Position.BOTTOM_CENTER
            y in separators.y2ToBorderRange && x in separators.x2ToBorderRange -> Position.BOTTOM_RIGHT
            else -> throw IllegalStateException("Can't determine the position for provided X = $x, Y = $y")
        }
    }
}