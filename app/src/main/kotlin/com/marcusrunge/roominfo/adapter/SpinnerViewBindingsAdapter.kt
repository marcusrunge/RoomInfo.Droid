package com.marcusrunge.roominfo.adapter

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter

object SpinnerViewBindingsAdapter {

    @BindingAdapter("setArrayAdapter")
    @JvmStatic
    fun bindArrayAdapter(spinner: Spinner, adapter: ArrayAdapter<CharSequence>?) {
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    @BindingAdapter("setSelection")
    @JvmStatic
    fun bindSelection(spinner: Spinner, selection: Int) {
        spinner.setSelection(selection)
    }

    @BindingAdapter("setsetOnItemSelectedListener")
    @JvmStatic
    fun bindsetOnItemSelectedListener(
        spinner: Spinner,
        onItemSelectedListener: AdapterView.OnItemSelectedListener
    ) {
        spinner.onItemSelectedListener = onItemSelectedListener
    }
}