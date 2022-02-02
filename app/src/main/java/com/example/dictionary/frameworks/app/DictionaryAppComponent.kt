package com.example.dictionary.frameworks.app

import android.app.Application
import com.example.dictionary.frameworks.datasource.DataSourceModule
import com.example.dictionary.frameworks.ui.ActivityModule
import com.example.dictionary.frameworks.ui.MainActivity
import com.example.dictionary.interactors.Interactor
import com.example.dictionary.interactors.InteractorModule
import com.example.dictionary.interfaceadapters.repositories.RepositoryModule
import com.example.dictionary.interfaceadapters.viewmodels.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        DataSourceModule::class,
        RepositoryModule::class,
        InteractorModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidInjectionModule::class
    ]
)
@Singleton
interface DictionaryAppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): DictionaryAppComponent
    }

    fun inject(dictionaryApplication: DictionaryApp)
}