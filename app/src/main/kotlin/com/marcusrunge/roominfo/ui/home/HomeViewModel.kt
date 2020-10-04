package com.marcusrunge.roominfo.ui.home

import android.os.Message
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.marcusrunge.roominfo.adapter.AgendaRecyclerViewAdapter
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.models.AgendaItem
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.models.TimeSpanItem
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.time.interfaces.Time
import com.marcusrunge.roominfo.ui.ViewModelBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.System.currentTimeMillis
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val applicationResource: ApplicationResource,
    private val preferences: Preferences,
    private val data: Data,
    private val time: Time
) : ViewModelBase() {
    private companion object {
        val FORMATTED_TIME = 1
        val FORMATTED_DATE = 2
    }

    private val agendaItems: MutableList<AgendaItem> = mutableListOf()
    private val timeSpanItems: MutableList<TimeSpanItem> = mutableListOf()

    @get:Bindable
    var agendaRecyclerViewAdapter: AgendaRecyclerViewAdapter? =
        AgendaRecyclerViewAdapter(agendaItems, {}, { position, id -> })
        set(value) {
            field = value
            notifyPropertyChanged(BR.agendaRecyclerViewAdapter)
        }

    @get:Bindable
    var logoFilePath: String? = preferences.logoFilePath
        set(value) {
            field = value
            notifyPropertyChanged(BR.logoFilePath)
        }

    @get:Bindable
    var companyName: String? = preferences.companyName
        set(value) {
            field = value
            notifyPropertyChanged(BR.companyName)
        }

    @get:Bindable
    var roomName: String? = preferences.roomName
        set(value) {
            field = value
            notifyPropertyChanged(BR.roomName)
        }

    @get:Bindable
    var roomNumber: String? = preferences.roomNumber
        set(value) {
            field = value
            notifyPropertyChanged(BR.roomNumber)
        }

    @get:Bindable
    var formattedTime: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.formattedTime)
        }

    @get:Bindable
    var formattedDate: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.formattedDate)
        }

    @get:Bindable
    var occupancyState: Int? = preferences.occupancyState
        set(value) {
            field = value
            notifyPropertyChanged(BR.occupancyState)
        }

    init {
        loadItems()
        time.dateTime.timeUnit = {
            val message = Message()
            message.what = UPDATE_VIEW
            message.arg1 = FORMATTED_TIME
            message.obj = it
            handler.sendMessage(message)
        }
        time.dateTime.dateUnit = {
            val message = Message()
            message.what = UPDATE_VIEW
            message.arg1 = FORMATTED_DATE
            message.obj = it
            handler.sendMessage(message)
        }
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

    override fun updateView(inputMessage: Message) {
        when (inputMessage.arg1) {
            FORMATTED_TIME -> formattedTime = inputMessage.obj as String
            FORMATTED_DATE -> formattedDate = inputMessage.obj as String
        }
    }
}