package com.example.dictionary.frameworks.app

import android.app.Application
import com.example.dictionary.frameworks.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DictionaryApp)
            modules(
                listOf(
                    roomDatabaseModule,
                    converterModule,
                    dataSourceModule,
                    repositoryModule,
                    androidNetworkStatusModule,
                    interactorModule,
                    viewModelFactoryModule
                )
            )
        }
    }
}