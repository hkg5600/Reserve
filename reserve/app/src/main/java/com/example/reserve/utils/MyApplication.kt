package com.example.reserve.utils

import android.app.Application
import com.example.reserve.di.myDiModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, myDiModule)
    }
}