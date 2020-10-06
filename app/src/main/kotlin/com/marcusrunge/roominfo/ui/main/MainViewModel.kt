package com.marcusrunge.roominfo.ui.main

import android.os.Bundle
import android.os.Message
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.ui.ViewModelBase
import javax.inject.Inject

class MainViewModel @Inject constructor(private val applicationResource: ApplicationResource) :
    ViewModelBase(),
    NavController.OnDestinationChangedListener {

    @get:Bindable
    var occupancy: Int? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.occupancy)
            }
        }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateView(inputMessage: Message) {
        TODO("Not yet implemented")
    }
}