package com.marcusrunge.roominfo.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class AgendaItem(
    private var _id: Long?,
    private var _title: String?,
    private var _start: Long?,
    private var _end: Long?,
    private var _allDayEvent: Boolean?,
    private var _overridden: Boolean?,
    private var _description: String?,
    private var _occupancy: Int?,
    private var _textColor: Int?,
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
    var allDayEvent: Boolean?
        get() = _allDayEvent
        set(value) {
            _allDayEvent = value
            notifyPropertyChanged(BR.allDayEvent)
        }

    @get:Bindable
    var overridden: Boolean?
        get() = _overridden
        set(value) {
            _overridden = value
            notifyPropertyChanged(BR.overridden)
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
    var textColor: Int?
        get() = _textColor
        set(value) {
            _textColor = value
            notifyPropertyChanged(BR.textColor)
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