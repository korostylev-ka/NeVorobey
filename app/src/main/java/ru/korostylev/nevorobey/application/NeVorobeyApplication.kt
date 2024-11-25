package ru.korostylev.nevorobey.application

import android.app.Application
import android.content.Context
import android.util.Log

class NeVorobeyApplication: Application() {
    private lateinit var context: Context

    fun getAppContext() = context
    override fun onCreate() {
        super.onCreate()
        Log.d("vorobey", "Context added")
        context = applicationContext
        Log.d("vorobey", "$context")

    }
}