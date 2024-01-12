package com.pats1337.vksynergy

import android.app.Application
import com.pats1337.vksynergy.base.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
    companion object{
        var prefs: Prefs? = null
    }
}
