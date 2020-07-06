package com.marcusrunge.roominfo.data.interfaces

import androidx.room.*
import com.marcusrunge.roominfo.data.models.AgendaItem

@Dao
interface AgendaItems {
    @Query("SELECT * FROM agendaitem")
    fun getAll(): List<AgendaItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(agendaItem: AgendaItem)

    @Update
    fun update(agendaItem: AgendaItem)

    @Delete
    fun delete(agendaItem: AgendaItem)

    @Query("DELETE FROM agendaitem WHERE id = :id")
    fun delete(id: Int)
}