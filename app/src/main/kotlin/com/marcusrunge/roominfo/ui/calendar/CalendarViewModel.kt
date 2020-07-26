package com.marcusrunge.roominfo.ui.calendar

import android.content.Context
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.marcusrunge.roominfo.adapter.AgendaRecyclerViewAdapter
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.models.AgendaItem
import com.marcusrunge.roominfo.ui.ViewModelBase
import javax.inject.Inject

class CalendarViewModel @Inject constructor(
    private val context: Context,
    private val data: Data
) : ViewModelBase() {
    private val agendaItems: MutableList<AgendaItem> = mutableListOf()

    @get:Bindable
    var agendaRecyclerViewAdapter: AgendaRecyclerViewAdapter? =
        AgendaRecyclerViewAdapter(agendaItems)
        set(value) {
            field = value
            notifyPropertyChanged(BR.agendaRecyclerViewAdapter)
        }

    init {
        data.agendaItems.getAll().forEach {
            agendaItems.add(
                AgendaItem(
                    it.Id,
                    it.Title,
                    it.Start,
                    it.End,
                    it.IsAllDayEvent,
                    it.IsOverridden,
                    it.Description,
                    it.Occupancy,
                    it.TimeStamp,
                    it.IsDeleted
                )
            )
        }
    }

    fun showAddAgendaItemDialog() {

    }

    private fun addAgendaItem(agendaItem: AgendaItem) {
        agendaItems.add(agendaItem)
        data.agendaItems.insert(
            com.marcusrunge.roominfo.data.models.AgendaItem(
                0,
                agendaItem.title,
                agendaItem.start,
                agendaItem.end,
                agendaItem.allDayEvent,
                agendaItem.overridden,
                agendaItem.description,
                agendaItem.occupancy,
                agendaItem.timeStamp,
                agendaItem.deleted
            )
        )
    }

    private fun updateAgendaItem(agendaItem: AgendaItem) {
        for (i in agendaItems.indices) {
            if (agendaItems[i].id == agendaItem.id) {
                agendaItems[i].title = agendaItem.title
                agendaItems[i].start = agendaItem.start
                agendaItems[i].end = agendaItem.end
                agendaItems[i].allDayEvent = agendaItem.allDayEvent
                agendaItems[i].overridden = agendaItem.overridden
                agendaItems[i].description = agendaItem.description
                agendaItems[i].occupancy = agendaItem.occupancy
                agendaItems[i].timeStamp = agendaItem.timeStamp
                agendaItems[i].deleted = agendaItem.deleted
                break
            }
        }
        data.agendaItems.update(
            com.marcusrunge.roominfo.data.models.AgendaItem(
                agendaItem.id!!,
                agendaItem.title,
                agendaItem.start,
                agendaItem.end,
                agendaItem.allDayEvent,
                agendaItem.overridden,
                agendaItem.description,
                agendaItem.occupancy,
                agendaItem.timeStamp,
                agendaItem.deleted
            )
        )
    }

    fun deleteAgendaItem(id: Long) {
        for (i in agendaItems.indices) {
            if (agendaItems[i].id == id) {
                agendaItems.removeAt(i)
                break
            }
        }
        data.agendaItems.delete(id.toInt())
    }
}