package com.marcusrunge.roominfo

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.marcusrunge.roominfo.dagger.DaggerRoomInfoApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class RoomInfoApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        DaggerRoomInfoApplicationComponent.create().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}