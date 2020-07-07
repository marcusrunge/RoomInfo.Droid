package com.marcusrunge.roominfo.data.interfaces

import androidx.room.*

import com.marcusrunge.roominfo.data.models.TimeSpanItem

@Dao
interface TimeSpanItems {
    @Query("SELECT * FROM timespanitem")
    fun getAll(): List<TimeSpanItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(timeSpanItem: TimeSpanItem)

    @Update
    fun update(timeSpanItem: TimeSpanItem)

    @Delete
    fun delete(timeSpanItem: TimeSpanItem)

    @Query("DELETE FROM timespanitem WHERE id = :id")
    fun delete(id: Int)

    @Query("DELETE FROM timespanitem")
    fun deleteAll()
}