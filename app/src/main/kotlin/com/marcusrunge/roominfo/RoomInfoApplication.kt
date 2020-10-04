package com.marcusrunge.roominfo

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.marcusrunge.roominfo.dagger.DaggerRoomInfoApplicationComponent
import com.marcusrunge.roominfo.models.ApplicationResource
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class RoomInfoApplication : Application(), HasAndroidInjector,
    Application.ActivityLifecycleCallbacks {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    companion object {
        const val OCCUPANCY_STATE_FREE = 0
        const val OCCUPANCY_STATE_PRESENT = 1
        const val OCCUPANCY_STATE_ABSENT = 2
        const val OCCUPANCY_STATE_BUSY = 3
        const val OCCUPANCY_STATE_OCCUPIED = 4
        const val OCCUPANCY_STATE_LOCKED = 5
        const val OCCUPANCY_STATE_HOMEOFFICE = 6
        lateinit var mainActivity: MainActivity
        val applicationResource = ApplicationResource()
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        DaggerRoomInfoApplicationComponent.create().inject(this)
        applicationResource.applicationContext = applicationContext
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
    override fun onActivityPaused(p0: Activity) {
        //TODO("Not yet implemented")
    }

    override fun onActivityStarted(p0: Activity) {
        //TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(p0: Activity) {
        //TODO("Not yet implemented")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        //TODO("Not yet implemented")
    }

    override fun onActivityStopped(p0: Activity) {
        //TODO("Not yet implemented")
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        if (p0::class == MainActivity::class) {
            mainActivity = p0 as MainActivity
            applicationResource.mainActivity = p0
        }
    }

    override fun onActivityResumed(p0: Activity) {
        //TODO("Not yet implemented")
    }
}