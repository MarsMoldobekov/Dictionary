package com.example.dictionary.frameworks.app

import android.content.Context
import com.example.dictionary.frameworks.datasource.DataSourceModule
import com.example.dictionary.frameworks.rx.CompositeDisposableProviderModule
import com.example.dictionary.frameworks.rx.SchedulerProviderModule
import com.example.dictionary.frameworks.ui.ActivityModule
import com.example.dictionary.frameworks.web.AndroidNetworkStatusModule
import com.example.dictionary.interactors.InteractorModule
import com.example.dictionary.interfaceadapters.repositories.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        DataSourceModule::class,
        RepositoryModule::class,
        AndroidNetworkStatusModule::class,
        InteractorModule::class,
        SchedulerProviderModule::class,
        CompositeDisposableProviderModule::class,
        ActivityModule::class,
        AndroidInjectionModule::class
    ]
)
@Singleton
interface DictionaryAppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(context: Context): Builder

        fun build(): DictionaryAppComponent
    }

    fun inject(dictionaryApplication: DictionaryApp)
}