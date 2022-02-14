package com.example.dictionary.frameworks.datasource

import com.example.dictionary.entities.AppState

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDatabase(appState: AppState)
}