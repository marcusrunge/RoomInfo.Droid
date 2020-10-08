package com.marcusrunge.roominfo.ui.main

import android.os.Bundle
import android.os.Message
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.ui.ViewModelBase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val applicationResource: ApplicationResource,
    private val preferences: Preferences
) :
    ViewModelBase(),
    NavController.OnDestinationChangedListener, (Any?) -> Unit {

    @get:Bindable
    var occupancy: Int? = preferences.occupancy
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.occupancy)
            }
        }

    init {
        preferences.addOnPreferenceChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateView(inputMessage: Message) {
        if (inputMessage.obj is Pair<*, *>) {
            when {
                ((inputMessage.obj as Pair<*, *>).first as String) == "occupancy" -> occupancy =
                    (inputMessage.obj as Pair<*, *>).second as Int
            }
        }
    }

    override fun invoke(p1: Any?) {
        val updateViewMessage = Message()
        updateViewMessage.what = UPDATE_VIEW
        updateViewMessage.obj = p1
        handler.sendMessage(updateViewMessage)
    }

    override fun onCleared() {
        preferences.removeOnPreferenceChangedListener(this)
        super.onCleared()
    }
}