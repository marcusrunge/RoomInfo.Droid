package com.marcusrunge.roominfo.implementations

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.marcusrunge.roominfo.adapter.AgendaRecyclerViewAdapter

class SwipeToDeleteCallback(val agendaRecyclerViewAdapter: AgendaRecyclerViewAdapter?) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        agendaRecyclerViewAdapter?.deleteItem(position)
    }

}