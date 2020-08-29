package com.marcusrunge.roominfo.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object TimeConverterBindingAdapter {
    @BindingAdapter("timeFromMillis")
    @JvmStatic
    fun timeFromMillis(textView: TextView, millis: Long) {
        val current = LocalDateTime.ofEpochSecond(millis, 0, OffsetDateTime.now().offset)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        textView.text = current.format(formatter)
    }
}