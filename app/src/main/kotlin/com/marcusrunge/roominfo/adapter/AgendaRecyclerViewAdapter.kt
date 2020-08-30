package com.marcusrunge.roominfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.models.AgendaItem

class AgendaRecyclerViewAdapter(
    private val agendaItems: MutableList<AgendaItem>,
    private val onClicked: (id: Long) -> Unit,
    private val onDeleted: (position: Int, id: Long) -> Unit
) :
    RecyclerView.Adapter<AgendaRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            R.layout.viewholder_agenda,
            parent,
            false
        )
        return ViewHolder(viewDataBinding)
    }

    override fun getItemCount(): Int {
        return agendaItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val agendaItem = agendaItems[position]
        holder.itemView.setOnClickListener {
            onClicked.invoke(agendaItem.id!!)
        }
        holder.bind(agendaItem)
    }

    fun deleteItem(position: Int) {
        onDeleted.invoke(position, agendaItems[position].id!!)
    }

    class ViewHolder internal constructor(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(`object`: Any?) {
            viewDataBinding.setVariable(BR.agendaitem, `object`)
            viewDataBinding.executePendingBindings()
        }
    }
}