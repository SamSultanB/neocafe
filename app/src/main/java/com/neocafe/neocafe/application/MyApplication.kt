package com.neocafe.neocafe.application

import android.app.Application
import com.neocafe.neocafe.di.retrofitModule
import com.neocafe.neocafe.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(listOf(retrofitModule, viewModule))
            androidContext(this@MyApplication)
        }
    }
}