package ru.artofmainstreams.androidacademy2020

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Разделитель между элеменнтами в GridLayoutManager (span = 2)
 *
 * @author Andrei Khromov on 24.04.2021
 */
class GridSpacingItemDecoration(private val offset: Int) : ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams
        if (layoutParams.spanIndex % 2 == 0) {
            outRect.top = offset
            outRect.left = offset
            outRect.right = offset / 2
        } else {
            outRect.top = offset
            outRect.left = offset / 2
            outRect.right = offset
        }
    }
}
