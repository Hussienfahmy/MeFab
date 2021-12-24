package com.hfahmy.mefab

import android.content.Context
import android.util.AttributeSet
import android.widget.PopupMenu
import android.widget.RelativeLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.iterator
import androidx.core.view.size

class MovableFloatingExpandedActionButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : RelativeLayout(context, attrs, defStyleAttr), Communicator {

    private var edgeFabChildClickListener: EdgeFloatingActionButton.OnClickListener? = null

    private lateinit var edgeFabList: MutableList<EdgeFloatingActionButton>

    private var closeAfterEdgeFabClick = false

    private val centerFab: CenterFloatingActionButton =
        CenterFloatingActionButton(context, attrs, defStyleAttr)

    private val edgePositions = mutableListOf<Position>()

    private lateinit var coordinatesGenerator: CoordinatesGenerator

    fun setOnEdgeClickListener(onEdgeFabClickListener: EdgeFloatingActionButton.OnClickListener) {
        this.edgeFabChildClickListener = onEdgeFabClickListener
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        coordinatesGenerator = CoordinatesGenerator(w, h)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = MeasureSpec.makeMeasureSpec(resources.getDimension(R.dimen.container_size).toInt(), MeasureSpec.EXACTLY)
        super.onMeasure(size, size)
        setMeasuredDimension(size, size)
    }

    init {
        addView(centerFab)

        context.withStyledAttributes(attrs, R.styleable.MovableFloatingExpandedActionButton) {
            closeAfterEdgeFabClick =
                this.getBoolean(R.styleable.MovableFloatingExpandedActionButton_closeAfterEdgeFabClick,
                    false)
            val menuId = this.getResourceId(R.styleable.MovableFloatingExpandedActionButton_menu, 0)
            generateEdgeFabs(menuId, attrs, defStyleAttr)
        }
    }

    private fun generateEdgeFabs(menuId: Int, attrs: AttributeSet?, defStyleAttr: Int) {
        val popupMenu = PopupMenu(context, null)
        popupMenu.inflate(menuId)
        edgeFabList = mutableListOf()
        val size = popupMenu.menu.size
        if (size > 3) throwMaxItemsException(size)
        popupMenu.menu.iterator().forEach { menuItem ->
            val edgeFab = EdgeFloatingActionButton(context, attrs, defStyleAttr).apply {
                setImageDrawable(menuItem.icon)
                id = menuItem.itemId
            }
            edgeFabList.add(edgeFab)
            addView(edgeFab)
        }
    }

    override fun onCenterFabChange(newPosition: Position, state: State) {
        if (state == State.CLOSED) {
            // clear so i can check equality when it expand again
            edgePositions.clear()
            val edgePositions = mutableListOf<Position>().apply {
                repeat(edgeFabList.size) { add(Position.CENTER) }
            }
            animateEdgeFabs(edgePositions)
            return
        }
        val newEdgePositions = when (newPosition) {
            Position.TOP_LEFT -> suitablePositionsForChildrenInTopLeft(edgeFabList.size)
            Position.TOP_CENTER -> suitablePositionsForChildrenInTopCenter(edgeFabList.size)
            Position.TOP_RIGHT -> suitablePositionsForChildrenInTopRight(edgeFabList.size)
            Position.CENTER_LEFT -> suitablePositionsForChildrenInCenterLeft(edgeFabList.size)
            Position.CENTER -> suitablePositionsForChildrenInCenter(edgeFabList.size)
            Position.CENTER_RIGHT -> suitablePositionsForChildrenInCenterRight(edgeFabList.size)
            Position.BOTTOM_RIGHT -> suitablePositionsForChildrenInBottomRight(edgeFabList.size)
            Position.BOTTOM_CENTER -> suitablePositionsForChildrenInBottomCenter(edgeFabList.size)
            Position.BOTTOM_LEFT -> suitablePositionsForChildrenInBottomLeft(edgeFabList.size)
        }

        // prevent animate to the same positions
        if (!edgePositions.containsAll(newEdgePositions)) {
            edgePositions.clear()
            edgePositions.addAll(newEdgePositions)
            animateEdgeFabs(edgePositions)
        }
    }

    private fun animateEdgeFabs(edgePositions: MutableList<Position>) {
        edgeFabList.zip(edgePositions).forEach { (edgeFab, position) ->
            edgeFab.animateTo(coordinatesGenerator.getCoordinates(position, edgeFab))
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
        }

        edgeFabChildClickListener?.onClick(fabId)
    }
}