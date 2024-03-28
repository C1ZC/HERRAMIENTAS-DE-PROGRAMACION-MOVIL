package com.abcstudios.camilo_zavala_herpm13051_s7

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "UserSessionPreferences"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // List of preferences
    private val IS_USER_LOGGED_IN = "isUserLoggedIn"
    private val USERNAME = "username"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    var isUserLoggedIn: Boolean
        get() = preferences.getBoolean(IS_USER_LOGGED_IN, false)
        set(value) = preferences.edit().putBoolean(IS_USER_LOGGED_IN, value).apply()

    var username: String?
        get() = preferences.getString(USERNAME, "")
        set(value) = preferences.edit().putString(USERNAME, value).apply()
}
