package com.marcusrunge.roominfo.adapter

import android.app.Activity
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.ui.main.MainViewModel

object NavViewBindingsAdapter {
    @BindingAdapter("bindNavController")
    @JvmStatic
    fun bindNavController(view: View, mainViewModel: MainViewModel) {
        val navController =
            (view.context as Activity).findNavController(R.id.nav_host_fragment)
        (view as BottomNavigationView).setupWithNavController(navController)
        navController.addOnDestinationChangedListener(mainViewModel)
    }
}