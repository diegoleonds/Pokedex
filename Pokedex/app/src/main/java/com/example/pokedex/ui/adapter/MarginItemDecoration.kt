package com.example.pokedex.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    val verticalMargin: Int,
    val lastRowBottomMargin: Int = verticalMargin
    ): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter!!.itemCount
        with(outRect) {
            when (position) {
                0, 1 -> {
                    top = verticalMargin
                    bottom = verticalMargin
                }
                /**
                 * itemCount is from adapter item count, that is an array size, so -1 and -2
                 * are used to get last an previous position because an array size is equals
                 * to elements number plus one
                 */
                itemCount - 1, itemCount - 2 -> bottom = lastRowBottomMargin
                else -> bottom = verticalMargin
            }
        }
    }
}