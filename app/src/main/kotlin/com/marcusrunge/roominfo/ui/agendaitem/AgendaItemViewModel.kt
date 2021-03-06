package com.marcusrunge.roominfo.ui.agendaitem

import android.os.Message
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.models.AgendaItem
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.time.interfaces.Time
import com.marcusrunge.roominfo.ui.ViewModelBase
import com.marcusrunge.roominfo.ui.datepicker.DatePickerFragment
import com.marcusrunge.roominfo.ui.timepicker.TimePickerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AgendaItemViewModel @Inject constructor(
    private val applicationResource: ApplicationResource,
    private val data: Data,
    private val time: Time
) : ViewModelBase(), AdapterView.OnItemSelectedListener {
    private var id: Long = 0
    private val current = LocalDateTime.now()
    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private var localStart: LocalDateTime = current
    private var localEnd: LocalDateTime = current

    @get:Bindable
    var occupancyAdapter: ArrayAdapter<CharSequence>? = ArrayAdapter.createFromResource(
        applicationResource.applicationContext!!,
        R.array.occupancy_states,
        R.layout.daynight_spinner_item
    )
        set(value) {
            field = value
            notifyPropertyChanged(BR.occupancyAdapter)
        }

    @get:Bindable
    var occupancySelection: Int? = 0
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.occupancySelection)
            }
        }

    @get:Bindable
    var agendaItemTitle: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.agendaItemTitle)
        }

    @get:Bindable
    var startDate: String? =
        LocalDateTime.ofEpochSecond(0, 0, OffsetDateTime.now().offset).format(dateFormatter)
        set(value) {
            field = value
            notifyPropertyChanged(BR.startDate)
        }

    @get:Bindable
    var startTime: String? =
        LocalDateTime.ofEpochSecond(0, 0, OffsetDateTime.now().offset).format(timeFormatter)
        set(value) {
            field = value
            notifyPropertyChanged(BR.startTime)
        }

    @get:Bindable
    var allDay: Boolean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.allDay)
        }

    @get:Bindable
    var endDate: String? =
        LocalDateTime.ofEpochSecond(0, 0, OffsetDateTime.now().offset).format(dateFormatter)
        set(value) {
            field = value
            notifyPropertyChanged(BR.endDate)
        }

    @get:Bindable
    var endTime: String? =
        LocalDateTime.ofEpochSecond(0, 0, OffsetDateTime.now().offset).format(timeFormatter)
        set(value) {
            field = value
            notifyPropertyChanged(BR.endTime)
        }

    @get:Bindable
    var occupancy: Int? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.occupancy)
        }

    @get:Bindable
    var description: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    fun saveAgendaItem() {
        if (id > 0) {
            updateAgendaItem(
                AgendaItem(
                    id,
                    agendaItemTitle,
                    localStart.toEpochSecond(OffsetDateTime.now().offset),
                    localEnd.toEpochSecond(OffsetDateTime.now().offset),
                    allDay,
                    false,
                    description,
                    occupancySelection,
                    null,
                    OffsetDateTime.now().toEpochSecond(),
                    false
                )
            )
        } else {
            addAgendaItem(
                AgendaItem(
                    0,
                    agendaItemTitle,
                    localStart.toEpochSecond(OffsetDateTime.now().offset),
                    localEnd.toEpochSecond(OffsetDateTime.now().offset),
                    allDay,
                    false,
                    description,
                    occupancySelection,
                    null,
                    OffsetDateTime.now().toEpochSecond(),
                    false
                )
            )
        }
    }

    fun openPicker(view: View) {
        when (view.id) {
            R.id.startDate -> DatePickerFragment { y: Int, m: Int, d: Int ->
                localStart = LocalDateTime.of(y, m, d, localStart.hour, localStart.minute)
                startDate = localStart.format(dateFormatter)
            }.show(
                applicationResource.mainActivity!!.supportFragmentManager,
                "startDate"
            )
            R.id.startTime -> TimePickerFragment { h: Int, m: Int ->
                CoroutineScope(Dispatchers.IO).launch {
                    if (time.checkFind.checkStart(
                            LocalDateTime.of(
                                localStart.year,
                                localStart.month,
                                localStart.dayOfMonth,
                                h,
                                m
                            )
                        )
                    ) {
                        localStart =
                            LocalDateTime.of(
                                localStart.year,
                                localStart.month,
                                localStart.dayOfMonth,
                                h,
                                m
                            )
                        startTime = localStart.format(timeFormatter)
                    }
                }
            }.show(
                applicationResource.mainActivity!!.supportFragmentManager,
                "startTime"
            )
            R.id.endDate -> DatePickerFragment { y: Int, m: Int, d: Int ->
                localEnd = LocalDateTime.of(y, m, d, localEnd.hour, localEnd.minute)
                endDate = localEnd.format(dateFormatter)
            }.show(
                applicationResource.mainActivity!!.supportFragmentManager,
                "endDate"
            )
            R.id.endTime -> TimePickerFragment { h: Int, m: Int ->
                CoroutineScope(Dispatchers.IO).launch {
                    if (time.checkFind.checkEnd(
                            LocalDateTime.of(
                                localEnd.year,
                                localEnd.month,
                                localEnd.dayOfMonth,
                                h,
                                m
                            )
                        )
                    ) {
                        localEnd =
                            LocalDateTime.of(
                                localEnd.year,
                                localEnd.month,
                                localEnd.dayOfMonth,
                                h,
                                m
                            )
                        endTime = localEnd.format(timeFormatter)
                    }
                }
            }.show(
                applicationResource.mainActivity!!.supportFragmentManager,
                "endTime"
            )
        }
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun setSelectedDate(selectedDate: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            localStart = time.checkFind.findStart(
                LocalDateTime.ofEpochSecond(
                    selectedDate,
                    0,
                    OffsetDateTime.now().offset
                )
            )
            localEnd = time.checkFind.end!!
            startDate = localStart.format(dateFormatter)
            startTime = localStart.format(timeFormatter)
            endDate = localEnd.format(dateFormatter)
            endTime = localEnd.format(timeFormatter)
        }
    }

    private fun addAgendaItem(agendaItem: AgendaItem) {
        CoroutineScope(Dispatchers.IO).launch {
            id = data.agendaItems.insert(
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
    }

    private fun updateAgendaItem(agendaItem: AgendaItem) {
        CoroutineScope(Dispatchers.IO).launch {
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
    }

    override fun updateView(inputMessage: Message) {
        //TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        p0?.findViewById<TextView>(android.R.id.text1)?.textSize = 18F
        p0?.findViewById<TextView>(android.R.id.text2)?.textSize = 18F
        if (p2 != occupancySelection) occupancySelection = p2
    }
}