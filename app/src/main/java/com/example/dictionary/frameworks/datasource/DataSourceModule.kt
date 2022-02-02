package com.example.dictionary.frameworks.datasource

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.di.NAME_LOCAL
import com.example.dictionary.frameworks.di.NAME_REMOTE
import com.example.dictionary.frameworks.room.RoomDatabaseDataSource
import com.example.dictionary.frameworks.web.WebDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideWebDataSource(): DataSource<List<Word>> {
        return WebDataSource()
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRoomDatabaseDataSource(): DataSource<List<Word>> {
        return RoomDatabaseDataSource()
    }
}