package com.marcusrunge.roominfo.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimeSpanItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val Id: Long,
    @ColumnInfo(name = "day_of_week") val DayOfWeek: Int?,
    @ColumnInfo(name = "start") val Start: Long?,
    @ColumnInfo(name = "end") val End: Long?,
    @ColumnInfo(name = "occupancy") val Occupancy: Int?,
    @ColumnInfo(name = "timestamp") val TimeStamp: Long?,
    @ColumnInfo(name = "deleted") val IsDeleted: Boolean?
)