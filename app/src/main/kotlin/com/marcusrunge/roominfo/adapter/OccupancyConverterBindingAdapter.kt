package com.marcusrunge.roominfo.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.marcusrunge.roominfo.R

object OccupancyConverterBindingAdapter {
    @BindingAdapter("occupancyFromInt")
    @JvmStatic
    fun occupancyFromInt(textView: TextView, occupancy: Int) {
        val occupancies = textView.context.resources.getStringArray(R.array.occupancy_states)
        textView.text = occupancies[occupancy]
    }
}