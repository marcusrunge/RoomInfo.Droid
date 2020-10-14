package com.marcusrunge.roominfo.adapter

import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.RoomInfoApplication


object BottomNavigationViewBindingsAdapter {
    @BindingAdapter("setOccupancy")
    @JvmStatic
    fun bindOccupancy(view: View?, occupancy: Int?) {
        if (view is BottomNavigationView) {
            val typedValue = TypedValue()
            var background = 0
            var unselected = 0
            when (occupancy) {
                RoomInfoApplication.OCCUPANCY_STATE_FREE -> {
                    view.context.theme.resolveAttribute(R.attr.freeForeground, typedValue, true)
                    background =
                        view.context.resources.getColor(R.color.freeBackground, view.context.theme)
                    unselected = view.context.resources.getColor(
                        R.color.freeUnSelected,
                        view.context.theme
                    )
                }
                RoomInfoApplication.OCCUPANCY_STATE_PRESENT -> {
                    view.context.theme.resolveAttribute(R.attr.presentForeground, typedValue, true)
                    background = view.context.resources.getColor(
                        R.color.presentBackground,
                        view.context.theme
                    )
                    unselected = view.context.resources.getColor(
                        R.color.presentUnSelected,
                        view.context.theme
                    )
                }
                RoomInfoApplication.OCCUPANCY_STATE_ABSENT -> {
                    view.context.theme.resolveAttribute(R.attr.absentForeground, typedValue, true)
                    background = view.context.resources.getColor(
                        R.color.absentBackground,
                        view.context.theme
                    )
                    unselected = view.context.resources.getColor(
                        R.color.absentUnSelected,
                        view.context.theme
                    )
                }
                RoomInfoApplication.OCCUPANCY_STATE_BUSY -> {
                    view.context.theme.resolveAttribute(R.attr.busyForeground, typedValue, true)
                    background =
                        view.context.resources.getColor(R.color.busyBackground, view.context.theme)
                    unselected = view.context.resources.getColor(
                        R.color.busyUnSelected,
                        view.context.theme
                    )
                }
                RoomInfoApplication.OCCUPANCY_STATE_OCCUPIED -> {
                    view.context.theme.resolveAttribute(R.attr.occupiedForeground, typedValue, true)
                    background = view.context.resources.getColor(
                        R.color.occupiedBackground,
                        view.context.theme
                    )
                    unselected = view.context.resources.getColor(
                        R.color.occupiedUnSelected,
                        view.context.theme
                    )
                }
                RoomInfoApplication.OCCUPANCY_STATE_LOCKED -> {
                    view.context.theme.resolveAttribute(R.attr.lockedForeground, typedValue, true)
                    background = view.context.resources.getColor(
                        R.color.lockedBackground,
                        view.context.theme
                    )
                    unselected = view.context.resources.getColor(
                        R.color.lockedUnSelected,
                        view.context.theme
                    )
                }
                RoomInfoApplication.OCCUPANCY_STATE_HOMEOFFICE -> {
                    view.context.theme.resolveAttribute(R.attr.homeForeground, typedValue, true)
                    background =
                        view.context.resources.getColor(R.color.homeBackground, view.context.theme)
                    unselected = view.context.resources.getColor(
                        R.color.homeUnSelected,
                        view.context.theme
                    )
                }
            }
            val colorStateList = ColorStateList(
                arrayOf(
                    intArrayOf(android.R.attr.state_pressed),
                    intArrayOf(android.R.attr.state_selected),
                    intArrayOf(-android.R.attr.state_selected)
                ), intArrayOf(
                    typedValue.data,
                    typedValue.data,
                    unselected
                )
            )
            view.setBackgroundColor(background)
            view.itemIconTintList = colorStateList
            view.itemTextColor = colorStateList
        }
    }
}