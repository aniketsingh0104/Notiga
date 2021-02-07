package com.dullabs.notiga.ui

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.compose.material.MaterialTheme
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dullabs.notiga.R
import kotlin.properties.Delegates
import com.dullabs.notiga.utils.dpToPx

abstract class SwipeCallback(private var mContext: Context) : ItemTouchHelper.Callback() {

    private var mClearPaint: Paint = Paint()
    private var mBackground: ColorDrawable = ColorDrawable()
    private var mBackgroundColor by Delegates.notNull<Int>()
    private var mDeleteDrawable: Drawable
    private var mIntrinsicWidthDelete by Delegates.notNull<Int>()
    private var mIntrinsicHeightDelete by Delegates.notNull<Int>()

    init {
        mBackgroundColor = Color.parseColor("#b80f0a")
        mClearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        mDeleteDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_delete)!!
        mIntrinsicWidthDelete = mDeleteDrawable.intrinsicWidth
        mIntrinsicHeightDelete = mDeleteDrawable.intrinsicHeight
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView: View = viewHolder.itemView
        val itemHeight: Int = itemView.height

        val isCanceled: Boolean = (dX.compareTo(0.0) == 0) && !isCurrentlyActive

        if (isCanceled) {
            clearCanvas(c, itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        mBackground.color = mBackgroundColor
        mBackground.setBounds(itemView.right + dX.toInt() - dpToPx(5, mContext), itemView.top + dpToPx(10, mContext), itemView.right - dpToPx(5, mContext), itemView.bottom - dpToPx(10, mContext))
        mBackground.draw(c)

        val deleteIconTop: Int = itemView.top + (itemHeight - mIntrinsicHeightDelete)/2
        val deleteIconMargin: Int = (itemHeight - mIntrinsicHeightDelete)/2
        val deleteIconLeft: Int = itemView.right - deleteIconMargin - mIntrinsicWidthDelete
        val deleteIconRight: Int = itemView.right - deleteIconMargin
        val deleteIconBottom: Int = deleteIconTop + mIntrinsicHeightDelete


        mDeleteDrawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
        mDeleteDrawable.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun clearCanvas(c: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        c.drawRect(left, top, right, bottom, mClearPaint)
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.7f
    }

}