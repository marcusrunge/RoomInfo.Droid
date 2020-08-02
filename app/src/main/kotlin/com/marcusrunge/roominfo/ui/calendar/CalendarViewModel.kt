package com.marcusrunge.roominfo.ui.calendar

import android.os.Message
import android.widget.CalendarView
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.NavController
import com.marcusrunge.roominfo.adapter.AgendaRecyclerViewAdapter
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.interfaces.OnBackClickedListener
import com.marcusrunge.roominfo.models.AgendaItem
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.ui.ViewModelBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import javax.inject.Inject

class CalendarViewModel @Inject constructor(
    private val applicationResource: ApplicationResource,
    private val data: Data,
    private val navController: NavController
) : ViewModelBase(), OnBackClickedListener, CalendarView.OnDateChangeListener {
    private val agendaItems: MutableList<AgendaItem> = mutableListOf()

    @get:Bindable
    var agendaRecyclerViewAdapter: AgendaRecyclerViewAdapter? =
        AgendaRecyclerViewAdapter(agendaItems) {
            val directions =
                CalendarFragmentDirections.actionNavigationCalendarToNavigationAgendaitem(it)
            navController.navigate(directions)
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.agendaRecyclerViewAdapter)
        }

    init {
        applicationResource.mainActivity?.addOnBackClickedListener(this)
        loadAgendaItems(
            LocalDate.now().year,
            LocalDate.now().monthValue,
            LocalDate.now().dayOfMonth
        )
    }

    private fun loadAgendaItems(year: Int, month: Int, dayOfMonth: Int) {
        agendaItems.clear()
        CoroutineScope(Dispatchers.IO).launch {
            data.agendaItems.getAll(
                LocalDateTime.of(year, month, dayOfMonth, 0, 0)
                    .toEpochSecond(OffsetDateTime.now().offset),
                LocalDateTime.of(year, month, dayOfMonth, 23, 59)
                    .toEpochSecond(OffsetDateTime.now().offset)
            ).forEach {
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

    fun addAgendaItem() {
        val directions =
            CalendarFragmentDirections.actionNavigationCalendarToNavigationAgendaitem(0)
        navController.navigate(directions)
    }

    fun editAgendaItem() {
        /*val directions = CalendarFragmentDirections.actionNavigationCalendarToNavigationAgendaitem(0)
        navController.navigate(directions)*/
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
        loadAgendaItems(
            LocalDate.now().year,
            LocalDate.now().monthValue,
            LocalDate.now().dayOfMonth
        )
    }

    override fun onCleared() {
        applicationResource.mainActivity?.removeOnBackClickedListener(this)
        super.onCleared()
    }

    override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
        loadAgendaItems(p1, p2 + 1, p3)
    }
}