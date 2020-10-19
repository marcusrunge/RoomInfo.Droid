package com.marcusrunge.roominfo.ui.home

import android.os.Message
import android.util.TypedValue
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.RoomInfoApplication
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
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val applicationResource: ApplicationResource,
    private val preferences: Preferences,
    private val data: Data,
    private val time: Time
) : ViewModelBase(), AdapterView.OnItemSelectedListener {
    private companion object {
        const val FORMATTED_TIME = 1
        const val FORMATTED_DATE = 2
    }

    private val agendaItems: MutableList<AgendaItem> = mutableListOf()
    private val timeSpanItems: MutableList<TimeSpanItem> = mutableListOf()
    private var isSpinnerInitialized = false
    private var textColor = 0

    @get:Bindable
    var occupancyAdapter: ArrayAdapter<CharSequence>? = ArrayAdapter.createFromResource(
        applicationResource.applicationContext!!,
        R.array.occupancy_titles,
        R.layout.custom_simple_spinner_item
    )
        set(value) {
            field = value
            notifyPropertyChanged(BR.occupancyAdapter)
        }

    @get:Bindable
    var agendaRecyclerViewAdapter: AgendaRecyclerViewAdapter? =
        AgendaRecyclerViewAdapter(agendaItems, {}, { _, _ -> })
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
    var occupancyTitle: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.occupancyTitle)
        }

    @get:Bindable
    var occupancyIcon: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.occupancyIcon)
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
    var occupancySelection: Int? = null
        set(value) {
            if (field != value) {
                field = value
                if (!(value != null && (value < 0 || value > applicationResource.applicationContext?.resources?.getStringArray(
                        R.array.occupancy_icons
                    )?.size!!))
                ) {
                    occupancyIcon =
                        applicationResource.applicationContext?.resources?.getStringArray(R.array.occupancy_icons)
                            ?.get(value!!)
                    occupancyTitle =
                        applicationResource.applicationContext?.resources?.getStringArray(R.array.occupancy_titles)
                            ?.get(value!!)
                }
                updateTextColor()
                agendaRecyclerViewAdapter?.notifyDataSetChanged()
                notifyPropertyChanged(BR.occupancySelection)
            }
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
            data.agendaItems.getThree(TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis()))
                .forEach {
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
                            textColor,
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (isSpinnerInitialized) {
            occupancySelection = -1
            occupancySelection = position
            preferences.occupancy = position
        } else {
            occupancySelection = preferences.occupancy
            isSpinnerInitialized = true
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun reset() {
        occupancySelection = preferences.standardOccupancy
    }

    private fun updateTextColor() {
        val typedValue = TypedValue()
        when (occupancySelection) {
            RoomInfoApplication.OCCUPANCY_STATE_FREE -> applicationResource.applicationContext?.theme?.resolveAttribute(
                R.attr.freeForeground,
                typedValue,
                true
            )
            RoomInfoApplication.OCCUPANCY_STATE_PRESENT -> applicationResource.applicationContext?.theme?.resolveAttribute(
                R.attr.presentForeground,
                typedValue,
                true
            )
            RoomInfoApplication.OCCUPANCY_STATE_ABSENT -> applicationResource.applicationContext?.theme?.resolveAttribute(
                R.attr.absentForeground,
                typedValue,
                true
            )
            RoomInfoApplication.OCCUPANCY_STATE_BUSY -> applicationResource.applicationContext?.theme?.resolveAttribute(
                R.attr.busyForeground,
                typedValue,
                true
            )
            RoomInfoApplication.OCCUPANCY_STATE_OCCUPIED -> applicationResource.applicationContext?.theme?.resolveAttribute(
                R.attr.occupiedForeground,
                typedValue,
                true
            )
            RoomInfoApplication.OCCUPANCY_STATE_LOCKED -> applicationResource.applicationContext?.theme?.resolveAttribute(
                R.attr.lockedForeground,
                typedValue,
                true
            )
            RoomInfoApplication.OCCUPANCY_STATE_HOMEOFFICE -> applicationResource.applicationContext?.theme?.resolveAttribute(
                R.attr.homeForeground,
                typedValue,
                true
            )
        }
        textColor = typedValue.data
    }
}