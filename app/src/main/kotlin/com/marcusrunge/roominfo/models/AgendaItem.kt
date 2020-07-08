package com.marcusrunge.roominfo.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class AgendaItem(
    private var _id: Long?,
    private var _title: String?,
    private var _start: Long?,
    private var _end: Long?,
    private var _isAllDayEvent: Boolean?,
    private var _isOverridden: Boolean?,
    private var _description: String?,
    private var _occupancy: Int?,
    private var _timeStamp: Long?,
    private var _isDeleted: Boolean?
) : BaseObservable() {
    @get:Bindable
    var id: Long?
        get() = _id
        set(value) {
            _id = value
            notifyPropertyChanged(BR.id)
        }

    @get:Bindable
    var title: String?
        get() = _title
        set(value) {
            _title = value
            notifyPropertyChanged(BR.title)
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
    var isAllDayEvent: Boolean
        get() = _isAllDayEvent
        set(value) {
            _isAllDayEvent = value
            notifyPropertyChanged(BR.isAllDayEvent)
        }

    @get:Bindable
    var isOverridden: Boolean?
        get() = _isOverridden
        set(value) {
            _isOverridden = value
            notifyPropertyChanged(BR.isOverridden)
        }

    @get:Bindable
    var description: String?
        get() = _description
        set(value) {
            _description = value
            notifyPropertyChanged(BR.description)
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
    var isDeleted: Boolean?
        get() = _isDeleted
        set(value) {
            _isDeleted = value
            notifyPropertyChanged(BR.isDeleted)
        }
}