package com.marcusrunge.roominfo.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marcusrunge.roominfo.data.implementations.DataFactoryImpl
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.data.models.AgendaItem
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AgendaItemsTest {
    private lateinit var data: Data

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        data = DataFactoryImpl.createSingleton(context)
    }

    @After
    fun closeDb() {
        data.agendaItems.deleteAll()
        data.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val agendaItem = AgendaItem(
            0, "Title", Long.MIN_VALUE, Long.MAX_VALUE,
            IsAllDayEvent = false,
            IsOverridden = false,
            Description = "Description",
            Occupancy = 0,
            TimeStamp = 0,
            IsDeleted = false
        )
        val id = data.agendaItems.insert(agendaItem)
        val agendaItems = data.agendaItems.getAll()
        assertThat(agendaItems[0].Id, equalTo(id))
    }
}