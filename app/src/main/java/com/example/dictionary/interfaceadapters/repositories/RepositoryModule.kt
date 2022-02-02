package com.example.dictionary.interfaceadapters.repositories

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.datasource.DataSource
import com.example.dictionary.frameworks.datasource.DataSourceModule
import com.example.dictionary.frameworks.di.NAME_LOCAL
import com.example.dictionary.frameworks.di.NAME_REMOTE
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [DataSourceModule::class])
class RepositoryModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSource: DataSource<List<Word>>): IRepository<List<Word>> {
        return Repository(dataSource)
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSource: DataSource<List<Word>>): IRepository<List<Word>> {
        return Repository(dataSource)
    }
}