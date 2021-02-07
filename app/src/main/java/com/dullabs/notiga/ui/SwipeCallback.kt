package com.dullabs.notiga.ui

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dullabs.notiga.R
import kotlin.properties.Delegates
import com.dullabs.notiga.utils.dpToPx

abstract class SwipeCallback(private var mContext: Context) : ItemTouchHelper.Callback() {

    private var mClearPaint: Paint = Paint()
    private var mDeleteBackground: ColorDrawable = ColorDrawable()
    private var mDeleteBackgroundColor by Delegates.notNull<Int>()
    private var mDeleteDrawable: Drawable
    private var mPauseBackground: ColorDrawable = ColorDrawable()
    private var mPauseBackgroundColor by Delegates.notNull<Int>()
    private var mPauseDrawable: Drawable
    private var mIntrinsicWidthDelete by Delegates.notNull<Int>()
    private var mIntrinsicHeightDelete by Delegates.notNull<Int>()
    private var mIntrinsicWidthPause by Delegates.notNull<Int>()
    private var mIntrinsicHeightPause by Delegates.notNull<Int>()

    init {
        mDeleteBackgroundColor = Color.parseColor("#F68167")
        mClearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        mDeleteDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_delete)!!
        mPauseBackgroundColor = Color.parseColor("#8BD343")
        mPauseDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_pause)!!
        mIntrinsicWidthDelete = mDeleteDrawable.intrinsicWidth
        mIntrinsicHeightDelete = mDeleteDrawable.intrinsicHeight
        mIntrinsicWidthPause = mPauseDrawable.intrinsicWidth
        mIntrinsicHeightPause = mPauseDrawable.intrinsicHeight
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
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

        val isCanceled: Boolean = (dX.compareTo(0.0) == 0) && !isCurrentlyActive

//        if (isCanceled) {
//            clearCanvas(
//                c,
//                itemView.right + dX,
//                itemView.top.toFloat(),
//                itemView.right.toFloat(),
//                itemView.bottom.toFloat()
//            )
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//            return
//        }
        revealSwipeOptions(c, itemView, dX)
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun clearCanvas(c: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        c.drawRect(left, top, right, bottom, mClearPaint)
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.7f
    }

    private fun revealSwipeOptions(c: Canvas, itemView: View, dX: Float) {
        val itemHeight: Int = itemView.height
        if (dX > 0) {
            mDeleteBackground.color = mDeleteBackgroundColor
            mDeleteBackground.setBounds(
                itemView.left + dpToPx(5, mContext),
                itemView.top + dpToPx(10, mContext),
                itemView.left + dX.toInt() + dpToPx(5, mContext),
                itemView.bottom - dpToPx(10, mContext)
            )
            mDeleteBackground.draw(c)

            val deleteIconTop: Int = itemView.top + (itemHeight - mIntrinsicHeightDelete) / 2
            val deleteIconMargin: Int = (itemHeight - mIntrinsicHeightDelete) / 2
            val deleteIconLeft: Int = itemView.left + deleteIconMargin
            val deleteIconRight: Int = itemView.left + deleteIconMargin + mIntrinsicWidthDelete
            val deleteIconBottom: Int = deleteIconTop + mIntrinsicHeightDelete

            mDeleteDrawable.setBounds(
                deleteIconLeft,
                deleteIconTop,
                deleteIconRight,
                deleteIconBottom
            )
            mDeleteDrawable.draw(c)
        } else {
            mPauseBackground.color = mPauseBackgroundColor
            mPauseBackground.setBounds(
                itemView.right + dX.toInt() - dpToPx(5, mContext),
                itemView.top + dpToPx(10, mContext),
                itemView.right - dpToPx(5, mContext),
                itemView.bottom - dpToPx(10, mContext)
            )
            mPauseBackground.draw(c)

            val pauseIconTop: Int = itemView.top + (itemHeight - mIntrinsicHeightPause) / 2
            val pauseIconMargin: Int = (itemHeight - mIntrinsicHeightPause) / 2
            val pauseIconLeft: Int = itemView.right - pauseIconMargin - mIntrinsicWidthPause
            val pauseIconRight: Int = itemView.right - pauseIconMargin
            val pauseIconBottom: Int = pauseIconTop + mIntrinsicHeightPause
            mPauseDrawable.setBounds(pauseIconLeft, pauseIconTop, pauseIconRight, pauseIconBottom)
            mPauseDrawable.draw(c)
        }
    }

}