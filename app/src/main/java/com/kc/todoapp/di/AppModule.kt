package com.kc.todoapp.di

import com.kc.todoapp.BuildConfig
import com.kc.todoapp.data.source.api.TodoApi
import com.kc.todoapp.util.network.SupportInterceptor
import com.kc.todoapp.util.network.createNetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

private val supportInterceptor = SupportInterceptor()

val networkModule: Module = module {
    single {
        createNetworkClient(
            androidContext(),
            BuildConfig.API_URL,
            BuildConfig.DEBUG,
            supportInterceptor
        )
    }
    single { get<Retrofit>().create(TodoApi::class.java) }
}