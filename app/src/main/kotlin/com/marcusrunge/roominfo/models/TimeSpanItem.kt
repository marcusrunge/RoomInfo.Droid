package com.marcusrunge.roominfo.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class TimeSpanItem(
    private var _id: Long?,
    private var _dayOfWeek: Int?,
    private var _start: Long?,
    private var _end: Long?,
    private var _occupancy: Int?,
    private var _timeStamp: Long?,
    private var _deleted: Boolean?
) : BaseObservable() {
    @get:Bindable
    var id: Long?
        get() = _id
        set(value) {
            _id = value
            notifyPropertyChanged(BR.id)
        }

    @get:Bindable
    var dayOfWeek: Int?
        get() = _dayOfWeek
        set(value) {
            _dayOfWeek = value
            notifyPropertyChanged(BR.dayOfWeek)
        }

    @get:Bindable
    var start: Long?
        get() = _start
        set(value) {
            _start = value
            notifyPropertyChanged(BR.start)
        }

    @get:Bindable
    var end: Long?
        get() = _end
        set(value) {
            _end = value
            notifyPropertyChanged(BR.end)
        }

    @get:Bindable
    var occupancy: Int?
        get() = _occupancy
        set(value) {
            _occupancy = value
            notifyPropertyChanged(BR.occupancy)
        }

    @get:Bindable
    var timeStamp: Long?
        get() = _timeStamp
        set(value) {
            _timeStamp = value
            notifyPropertyChanged(BR.timeStamp)
        }

    @get:Bindable
    var deleted: Boolean?
        get() = _deleted
        set(value) {
            _deleted = value
            notifyPropertyChanged(BR.deleted)
        }
}