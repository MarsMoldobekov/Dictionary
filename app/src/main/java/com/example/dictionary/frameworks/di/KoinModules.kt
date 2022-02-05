package com.example.dictionary.frameworks.di

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.datasource.DataSource
import com.example.dictionary.frameworks.room.RoomDatabaseDataSource
import com.example.dictionary.frameworks.web.AndroidNetworkStatus
import com.example.dictionary.frameworks.web.IAndroidNetworkStatus
import com.example.dictionary.frameworks.web.WebDataSource
import com.example.dictionary.interactors.IInteractor
import com.example.dictionary.interactors.Interactor
import com.example.dictionary.interfaceadapters.repositories.IRepository
import com.example.dictionary.interfaceadapters.repositories.Repository
import com.example.dictionary.interfaceadapters.viewmodels.MainViewModelFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single<DataSource<List<Word>>>(named(NAME_REMOTE)) {
        WebDataSource()
    }
    single<DataSource<List<Word>>>(named(NAME_LOCAL)) {
        RoomDatabaseDataSource()
    }
}

val repositoryModule = module {
    single<IRepository<List<Word>>>(named(NAME_REMOTE)) {
        Repository(dataSource = get(qualifier = named(NAME_REMOTE)))
    }
    single<IRepository<List<Word>>>(named(NAME_LOCAL)) {
        Repository(dataSource = get(qualifier = named(NAME_LOCAL)))
    }
}

val androidNetworkStatusModule = module {
    single<IAndroidNetworkStatus> {
        AndroidNetworkStatus(context = androidContext())
    }
}

val interactorModule = module {
    single<IInteractor<List<Word>>> {
        Interactor(
            remoteRepository = get(qualifier = named(NAME_REMOTE)),
            localRepository = get(qualifier = named(NAME_LOCAL)),
            androidNetworkStatus = get()
        )
    }
}

val viewModelFactoryModule = module {
    factory {
        MainViewModelFactory(interactor = get())
    }
}