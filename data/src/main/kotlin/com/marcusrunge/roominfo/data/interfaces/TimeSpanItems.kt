package com.marcusrunge.roominfo.data.interfaces

import androidx.room.*

import com.marcusrunge.roominfo.data.models.TimeSpanItem

@Dao
interface TimeSpanItems {
    @Query("SELECT * FROM timespanitem")
    fun getAll(): List<TimeSpanItem>

    @Insert
    fun insert(timeSpanItem: TimeSpanItem)

    @Update
    fun update(timeSpanItem: TimeSpanItem)

    @Delete
    fun delete(timeSpanItem: TimeSpanItem)
}