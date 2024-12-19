package com.n1kromant.instore

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EntryPoint: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}