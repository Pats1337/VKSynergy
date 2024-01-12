package com.pats1337.vksynergy.base

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var userId: Long
        get() = sharedPrefs.getLong(KEY_USER_ID, 0)
        set(value) = sharedPrefs.edit().putLong(KEY_USER_ID, value).apply()

    companion object {
        private const val PREFS_FILENAME = "app_prefs"
        private const val KEY_USER_ID = "user_id"
    }
}