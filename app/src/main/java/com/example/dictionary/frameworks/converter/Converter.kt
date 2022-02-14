package com.example.dictionary.frameworks.converter

import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.HistoryEntity
import com.example.dictionary.entities.Word

interface Converter {
    fun mapEntityToDataModel(all: List<HistoryEntity>): List<Word>
    fun convertDataModelToEntity(appState: AppState): HistoryEntity?
}