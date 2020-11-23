package com.kc.todoapp

import android.app.Application
import com.kc.todoapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class ToDoApplication:Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ToDoApplication)
            modules(listOf(networkModule))
        }
    }
}