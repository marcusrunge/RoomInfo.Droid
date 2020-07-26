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
}