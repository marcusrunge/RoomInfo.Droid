package com.marcusrunge.roominfo.data.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

data class TimeSpanItem(
    @PrimaryKey @ColumnInfo(name = "id") val Id: Int,
    @ColumnInfo(name = "day_of_week") val DayOfWeek: Int?,
    @ColumnInfo(name = "start") val Start: OffsetDateTime?,
    @ColumnInfo(name = "end") val End: OffsetDateTime?,
    @ColumnInfo(name = "occupancy") val Occupancy: Int?,
    @ColumnInfo(name = "timestamp") val TimeStamp: Long?,
    @ColumnInfo(name = "deleted") val IsDeleted: Boolean?
)