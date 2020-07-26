package com.marcusrunge.roominfo.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.ui.ViewModelBase
import javax.inject.Inject

class MainViewModel @Inject constructor(private val applicationResource: ApplicationResource) :
    ViewModelBase(),
    NavController.OnDestinationChangedListener {
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateView(obj: Any) {
        TODO("Not yet implemented")
    }
}