package com.pats1337.vksynergy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp

class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
