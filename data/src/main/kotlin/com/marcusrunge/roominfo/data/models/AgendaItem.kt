package com.marcusrunge.roominfo.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AgendaItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val Id: Int,
    @ColumnInfo(name = "title") val Title: String?,
    @ColumnInfo(name = "start") val Start: Long?,
    @ColumnInfo(name = "end") val End: Long?,
    @ColumnInfo(name = "all_day_event") val IsAllDayEvent: Boolean?,
    @ColumnInfo(name = "overridden") val IsOverridden: Boolean?,
    @ColumnInfo(name = "description") val Description: String?,
    @ColumnInfo(name = "occupancy") val Occupancy: Int?,
    @ColumnInfo(name = "timestamp") val TimeStamp: Long?,
    @ColumnInfo(name = "deleted") val IsDeleted: Boolean?
)