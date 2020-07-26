package com.marcusrunge.roominfo.ui.agendaitem

import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.models.AgendaItem
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.ui.ViewModelBase
import javax.inject.Inject

class AgendaItemViewModel @Inject constructor(
    private val applicationResource: ApplicationResource,
    private val data: Data
) : ViewModelBase() {
    private fun addAgendaItem(agendaItem: AgendaItem) {
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

    override fun updateView(obj: Any) {
        TODO("Not yet implemented")
    }
}