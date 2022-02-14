package com.example.dictionary.interfaceadapters.repositories

import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.datasource.DataSourceLocal

class RepositoryLocal(
    private val dataSourceLocal: DataSourceLocal<List<Word>>
) : IRepositoryLocal<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSourceLocal.getData(word)
    }

    override suspend fun saveToDatabase(appState: AppState) {
        dataSourceLocal.saveToDatabase(appState)
    }
}