package com.marcusrunge.roominfo.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

object TextViewBindingsAdapter {
    @BindingAdapter("setTextColor")
    @JvmStatic
    fun bindTextColor(textView: TextView?, textColor: Int?) {
        if (textView != null && textColor != null && textColor > 0) {
            textView.setTextColor(textColor)
        }
    }
}