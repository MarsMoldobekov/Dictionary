package com.example.dictionary.frameworks.web

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidNetworkStatusModule {

    @Singleton
    @Provides
    internal fun provideAndroidNetworkStatus(context: Context): AndroidNetworkStatus {
        return AndroidNetworkStatus(context)
    }
}