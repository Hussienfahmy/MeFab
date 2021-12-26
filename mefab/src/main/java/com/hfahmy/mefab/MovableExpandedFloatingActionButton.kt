package com.hfahmy.mefab

import android.content.Context
import android.transition.TransitionManager
import android.util.AttributeSet
import android.widget.PopupMenu
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.doOnPreDraw
import androidx.core.view.iterator
import androidx.core.view.size
import androidx.core.view.updateLayoutParams
import com.hfahmy.mefab.enums.Position
import com.hfahmy.mefab.fabs.CenterFloatingActionButton
import com.hfahmy.mefab.fabs.EdgeFloatingActionButton
import com.hfahmy.mefab.fabs.OnEdgeFabClickListener
import com.hfahmy.mefab.interfaces.Communicator
import com.hfahmy.mefab.models.Point
import com.hfahmy.mefab.utils.*
import kotlin.properties.Delegates

@OptIn(MeFabRestricted::class)
public class MovableFloatingExpandedActionButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : MotionLayout(context, attrs, defStyleAttr), Communicator {

    // listener to pass the clicks from edge fabs to user
    private var edgeFabChildClickListener: OnEdgeFabClickListener? = null

    // ids of the edge fabs, getting it from the supplied menu, i need it to make constraints
    private lateinit var edgeFabIds: List<Int>

    // supplied by the user, if true change the state of close after the edge fab clicked
    private var closeAfterEdgeFabClick by Delegates.notNull<Boolean>()

    // the Center Fab !!
    private val centerFab: CenterFloatingActionButton =
        CenterFloatingActionButton(context, attrs, defStyleAttr)

    // contains the current positions of the edge fabs
    private val edgePositions = mutableListOf<Position>()

    public fun setOnEdgeFabClickListener(onEdgeFabClickListener: OnEdgeFabClickListener) {
        this.edgeFabChildClickListener = onEdgeFabClickListener
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // specify a fixed width and height
        val size = MeasureSpec.makeMeasureSpec(
            resources.getDimension(R.dimen.container_size).toInt(),
            MeasureSpec.EXACTLY
        )
        super.onMeasure(size, size)
        setMeasuredDimension(size, size)
    }

    init {
        //add center fab
        addView(centerFab)
        // add it's constraint to make it in the center
        centerFab.updateLayoutParams<LayoutParams> {
            startToStart = id
            endToEnd = id
            topToTop = id
            bottomToBottom = id
        }

        // get user values from xml attributes
        context.withStyledAttributes(attrs, R.styleable.MovableFloatingExpandedActionButton) {
            closeAfterEdgeFabClick =
                this.getBoolean(
                    R.styleable.MovableFloatingExpandedActionButton_closeAfterEdgeFabClick,
                    false
                )
            val menuId = this.getResourceId(R.styleable.MovableFloatingExpandedActionButton_menu, 0)
            generateEdgeFabs(menuId, attrs, defStyleAttr)
        }

        // i need run this after drawing to make take effect !
        doOnPreDraw {
            // load the transition from xml
            loadLayoutDescription(R.xml.scene)
            // set the constraints of the edge fabs to make them in the center
            initConstraintStart()
        }
    }

    /**
     * Generate edge fabs.
     * Generate and add teh edge fabs
     */
    private fun generateEdgeFabs(menuId: Int, attrs: AttributeSet?, defStyleAttr: Int) {
        // inflating given menu to access the id and icons
        val popupMenu = PopupMenu(context, null)
        popupMenu.inflate(menuId)
        // check the size of menu
        val size = popupMenu.menu.size
        if (size > 3) throwNumberOfItemsItemsException(size)
        // ids of the fabs
        val ids = mutableListOf<Int>()
        popupMenu.menu.iterator()
            .forEach { menuItem ->
                val edgeFab = EdgeFloatingActionButton(context, attrs, defStyleAttr).apply {
                    setImageDrawable(menuItem.icon)
                    id = menuItem.itemId
                    ids.add(menuItem.itemId)
                    layoutParams = LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                    ).apply {
                        startToStart = this@MovableFloatingExpandedActionButton.id
                        endToEnd = this@MovableFloatingExpandedActionButton.id
                        topToTop = this@MovableFloatingExpandedActionButton.id
                        bottomToBottom = this@MovableFloatingExpandedActionButton.id
                    }
                }
                addView(edgeFab)
            }
        edgeFabIds = ids
    }

    private fun initConstraintStart() {
        getConstraintSet(R.id.start)?.let { constraintSet ->
            edgeFabIds.forEach {
                constraintSet.setConstraintsDefault(it, this.id)
                constraintSet.applyTo(this)
            }
        }
    }

    override fun onCenterFabPositionChange(newPosition: Position) {
        val newEdgePositions = when (newPosition) {
            Position.TOP_LEFT -> suitablePositionsForChildrenInTopLeft(edgeFabIds.size)
            Position.TOP_CENTER -> suitablePositionsForChildrenInTopCenter(edgeFabIds.size)
            Position.TOP_RIGHT -> suitablePositionsForChildrenInTopRight(edgeFabIds.size)
            Position.CENTER_LEFT -> suitablePositionsForChildrenInCenterLeft(edgeFabIds.size)
            Position.CENTER -> suitablePositionsForChildrenInCenter(edgeFabIds.size)
            Position.CENTER_RIGHT -> suitablePositionsForChildrenInCenterRight(edgeFabIds.size)
            Position.BOTTOM_RIGHT -> suitablePositionsForChildrenInBottomRight(edgeFabIds.size)
            Position.BOTTOM_CENTER -> suitablePositionsForChildrenInBottomCenter(edgeFabIds.size)
            Position.BOTTOM_LEFT -> suitablePositionsForChildrenInBottomLeft(edgeFabIds.size)
        }

        // prevent animate to the same positions
        // as it cause visual glitches and will cause performance load
        if (!edgePositions.containsAll(newEdgePositions)) {
            edgePositions.clear()
            edgePositions.addAll(newEdgePositions)
            changeConstraintsOfEdgeFabs(edgePositions)
        }
    }

    private fun changeConstraintsOfEdgeFabs(edgePositions: MutableList<Position>) {
        getConstraintSet(R.id.end)?.let {
            edgeFabIds.zip(edgePositions).forEach { (edgeFabId, position) ->
                it.clearConstraints(edgeFabId)
                it.setNewConstraints(edgeFabId, id, position)
                // animate the transition to the new positions if the state is opened
                TransitionManager.beginDelayedTransition(this)
                it.applyTo(this)
            }
        }
    }

    override fun onContainerMove(newPoint: Point) {
        animate()
            .x(newPoint.x)
            .y(newPoint.y)
            .setDuration(0)
            .start()
    }

    override fun onEdgeFabClick(fabId: Int) {
        if (closeAfterEdgeFabClick) {
            centerFab.inverseState()
            this.transitionToStart()
        }

        // trigger the user click listener
        edgeFabChildClickListener?.onClick(fabId)
    }
}