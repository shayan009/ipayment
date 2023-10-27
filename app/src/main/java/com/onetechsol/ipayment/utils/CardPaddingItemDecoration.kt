package com.techexactly.tryyourluckowner.utils


import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View

class CardPaddingItemDecoration(context: Context) :
    androidx.recyclerview.widget.RecyclerView.ItemDecoration() {

    private val paddingBetweenItems: Int

    init {
        this.paddingBetweenItems =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                8f,
                context.resources.displayMetrics
            ).toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: androidx.recyclerview.widget.RecyclerView,
        state: androidx.recyclerview.widget.RecyclerView.State
    ) {
        outRect.set(0, 0, 0, paddingBetweenItems)
    }
}
