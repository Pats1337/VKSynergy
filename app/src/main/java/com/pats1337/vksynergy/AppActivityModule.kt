package com.pats1337.vksynergy

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object AppActivityModule {

    @Provides
    fun provideAppActivity(): AppActivity {
        return AppActivity()
    }
}