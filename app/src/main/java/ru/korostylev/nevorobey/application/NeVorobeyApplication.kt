package ru.korostylev.nevorobey.application

import android.app.Application
import android.content.Context
import android.util.Log
import com.my.tracker.MyTracker

private val SDK_KEY = "31292590068946827272"
class NeVorobeyApplication: Application() {
    private lateinit var context: Context

    fun getAppContext() = context
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        val trackerParams = MyTracker.getTrackerParams()
        val trackerConfig = MyTracker.getTrackerConfig()
        MyTracker.initTracker(SDK_KEY, this)

    }
}