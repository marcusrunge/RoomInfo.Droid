package com.marcusrunge.roominfo.ui.calendar

import android.os.Message
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.NavController
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.adapter.AgendaRecyclerViewAdapter
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.interfaces.OnBackClickedListener
import com.marcusrunge.roominfo.models.AgendaItem
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.ui.ViewModelBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CalendarViewModel @Inject constructor(
    private val applicationResource: ApplicationResource,
    private val data: Data,
    private val navController: NavController
) : ViewModelBase(), OnBackClickedListener {
    private val agendaItems: MutableList<AgendaItem> = mutableListOf()

    @get:Bindable
    var agendaRecyclerViewAdapter: AgendaRecyclerViewAdapter? =
        AgendaRecyclerViewAdapter(agendaItems)
        set(value) {
            field = value
            notifyPropertyChanged(BR.agendaRecyclerViewAdapter)
        }

    init {
        applicationResource.mainActivity?.addOnBackClickedListener(this)
        loadAgendaItems()
    }

    private fun loadAgendaItems() {
        CoroutineScope(Dispatchers.IO).launch {
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
            val updateViewMessage = Message()
            updateViewMessage.what = UPDATE_VIEW
            updateViewMessage.obj = Any()
            handler.sendMessage(updateViewMessage)
        }
    }

    fun navigateToAgendaItem() {
        navController.navigate(R.id.navigation_agendaitem, null)
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

    override fun updateView(obj: Any) {
        agendaRecyclerViewAdapter?.notifyDataSetChanged()
    }

    override fun onBackClicked() {
        loadAgendaItems()
    }

    override fun onCleared() {
        applicationResource.mainActivity?.removeOnBackClickedListener(this)
        super.onCleared()
    }
}