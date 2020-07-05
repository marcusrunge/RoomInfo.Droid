package com.marcusrunge.roominfo.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity
data class AgendaItem(
    @PrimaryKey @ColumnInfo(name = "id") val Id: Int,
    @ColumnInfo(name = "title") val Title: String?,
    @ColumnInfo(name = "start") val Start: OffsetDateTime?,
    @ColumnInfo(name = "end") val End: OffsetDateTime?,
    @ColumnInfo(name = "all_day_event") val IsAllDayEvent: Boolean?,
    @ColumnInfo(name = "overridden") val IsOverridden: Boolean?,
    @ColumnInfo(name = "description") val Description: String?,
    @ColumnInfo(name = "occupancy") val Occupancy: Int?,
    @ColumnInfo(name = "timestamp") val TimeStamp: Long?,
    @ColumnInfo(name = "deleted") val IsDeleted: Boolean?
)