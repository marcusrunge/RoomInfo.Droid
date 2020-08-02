package com.marcusrunge.roominfo.adapter

import android.widget.CalendarView
import androidx.databinding.BindingAdapter

object CalendarViewBindingsAdapter {
    @BindingAdapter("setOnDateChangeListener")
    @JvmStatic
    fun bindOnDateChangeListener(
        calendarView: CalendarView,
        onDateChangeListener: CalendarView.OnDateChangeListener
    ) {
        calendarView.setOnDateChangeListener(onDateChangeListener)
    }
}