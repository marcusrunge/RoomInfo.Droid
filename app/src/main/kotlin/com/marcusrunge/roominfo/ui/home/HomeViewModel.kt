package com.marcusrunge.roominfo.ui.home

import android.os.Message
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.marcusrunge.roominfo.adapter.AgendaRecyclerViewAdapter
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.interfaces.SettingsService
import com.marcusrunge.roominfo.models.AgendaItem
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.models.TimeSpanItem
import com.marcusrunge.roominfo.ui.ViewModelBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.System.currentTimeMillis
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val applicationResource: ApplicationResource,
    private val settingsService: SettingsService,
    private val data: Data
) : ViewModelBase() {
    private val agendaItems: MutableList<AgendaItem> = mutableListOf()
    private val timeSpanItems: MutableList<TimeSpanItem> = mutableListOf()

    @get:Bindable
    var agendaRecyclerViewAdapter: AgendaRecyclerViewAdapter? =
        AgendaRecyclerViewAdapter(agendaItems) {}
        set(value) {
            field = value
            notifyPropertyChanged(BR.agendaRecyclerViewAdapter)
        }

    init {
        loadItems()
    }

    private fun loadItems() {
        CoroutineScope(Dispatchers.IO).launch {
            data.agendaItems.getThree(currentTimeMillis()).forEach {
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
            data.timeSpanItems.getAll().forEach {
                timeSpanItems.add(
                    TimeSpanItem(
                        it.Id,
                        it.DayOfWeek,
                        it.Start,
                        it.End,
                        it.Occupancy,
                        it.TimeStamp,
                        it.IsDeleted
                    )
                )
            }
            val updateViewMessage = Message()
            updateViewMessage.what = UPDATE_VIEW
            updateViewMessage.obj = Any()
            handler.sendMessage(updateViewMessage)
        }
    }

    override fun updateView(obj: Any) {

    }
}