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

class RoomInfoApplication : Application(), HasAndroidInjector,
    Application.ActivityLifecycleCallbacks {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        DaggerRoomInfoApplicationComponent.create().inject(this)
        registerActivityLifecycleCallbacks(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onActivityPaused(activity: Activity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStarted(activity: Activity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityDestroyed(activity: Activity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStopped(activity: Activity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResumed(activity: Activity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}