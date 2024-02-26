package com.pats1337.vksynergy.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModules {

    private const val SHARED_PREFERENCES_FILE_NAME = "sharedPreferences"

    @Provides
    fun providesSharedPreferences(context: Application): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }
}