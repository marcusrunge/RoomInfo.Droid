package com.marcusrunge.roominfo.adapter

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcusrunge.roominfo.R


object RecyclerViewBindingsAdapter {
    @BindingAdapter("setAdapter")
    @JvmStatic
    fun bindAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
        val divider = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.VERTICAL
        )
        divider.setDrawable(
            ContextCompat.getDrawable(
                recyclerView.context,
                R.drawable.shape_transparentlinedivider
            )!!
        )
        recyclerView.addItemDecoration(divider)
    }

    @BindingAdapter("setItemTouchHelper")
    @JvmStatic
    fun bindItemTouchHelper(recyclerView: RecyclerView, itemTouchHelper: ItemTouchHelper?) {
        itemTouchHelper?.attachToRecyclerView(recyclerView)
    }
}