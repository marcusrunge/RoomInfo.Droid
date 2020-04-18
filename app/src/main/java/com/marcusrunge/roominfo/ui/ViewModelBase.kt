package com.marcusrunge.roominfo.ui

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel

abstract class ViewModelBase : ViewModel(), Observable {
    private val propertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.remove(callback)
    }

    protected fun notifyPropertiesChanged() {
        synchronized(this) {
            propertyChangeRegistry.notifyCallbacks(this, 0, null)
        }
    }

    protected fun notifyPropertyChanged(propertyId: Int) {
        synchronized(this) {
            propertyChangeRegistry.notifyCallbacks(this, propertyId, null)
        }
    }
}