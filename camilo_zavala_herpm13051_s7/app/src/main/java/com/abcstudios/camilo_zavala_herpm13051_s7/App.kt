package com.abcstudios.camilo_zavala_herpm13051_s7

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}
