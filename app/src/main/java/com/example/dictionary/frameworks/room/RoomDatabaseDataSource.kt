package com.example.dictionary.frameworks.room

import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.converter.Converter
import com.example.dictionary.frameworks.datasource.DataSourceLocal

class RoomDatabaseDataSource(
    private val historyDao: HistoryDao,
    private val converter: Converter
) : DataSourceLocal<List<Word>> {
    override suspend fun getData(word: String): List<Word> {
        return converter.mapEntityToDataModel(historyDao.all())
    }

    override suspend fun saveToDatabase(appState: AppState) {
        converter.convertDataModelToEntity(appState)?.let { historyDao.insert(it) }
    }
}