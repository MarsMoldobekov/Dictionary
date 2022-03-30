package com.example.dictionary.frameworks.di

import androidx.room.Room
import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.converter.Converter
import com.example.dictionary.frameworks.converter.DataModelAndEntityConverter
import com.example.dictionary.frameworks.datasource.DataSource
import com.example.dictionary.frameworks.datasource.DataSourceLocal
import com.example.dictionary.frameworks.room.DATABASE_NAME
import com.example.dictionary.frameworks.room.HistoryDatabase
import com.example.dictionary.frameworks.room.RoomDatabaseDataSource
import com.example.dictionary.frameworks.web.AndroidNetworkStatus
import com.example.dictionary.frameworks.web.IAndroidNetworkStatus
import com.example.dictionary.frameworks.web.WebDataSource
import com.example.dictionary.interactors.HistoryInteractor
import com.example.dictionary.interactors.IInteractor
import com.example.dictionary.interactors.Interactor
import com.example.dictionary.interfaceadapters.repositories.IRepository
import com.example.dictionary.interfaceadapters.repositories.IRepositoryLocal
import com.example.dictionary.interfaceadapters.repositories.Repository
import com.example.dictionary.interfaceadapters.repositories.RepositoryLocal
import com.example.dictionary.interfaceadapters.viewmodels.HistoryViewModelFactory
import com.example.dictionary.interfaceadapters.viewmodels.MainViewModelFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), HistoryDatabase::class.java, DATABASE_NAME).build()
    }
    single {
        get<HistoryDatabase>().historyDao()
    }
}

val converterModule = module {
    factory<Converter> {
        DataModelAndEntityConverter()
    }
}

val dataSourceModule = module {
    single<DataSource<List<Word>>> {
        WebDataSource()
    }
    single<DataSourceLocal<List<Word>>> {
        RoomDatabaseDataSource(historyDao = get(), converter = get())
    }
}

val repositoryModule = module {
    single<IRepository<List<Word>>> {
        Repository(dataSource = get())
    }
    single<IRepositoryLocal<List<Word>>> {
        RepositoryLocal(dataSourceLocal = get())
    }
}

val androidNetworkStatusModule = module {
    single<IAndroidNetworkStatus> {
        AndroidNetworkStatus(context = androidContext())
    }
}

val interactorModule = module {
    factory<IInteractor<AppState>> {
        Interactor(
            remoteRepository = get(),
            localRepository = get()
        )
    }
    factory {
        HistoryInteractor(
            repositoryRemote = get(),
            repositoryLocal = get()
        )
    }
}

val viewModelFactoryModule = module {
    single {
        MainViewModelFactory(interactor = get())
    }
    single {
        HistoryViewModelFactory(interactor = get(), converter = get())
    }
}