package com.kc.todoapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class ToDoApplication:Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ToDoApplication)
        }
    }
}