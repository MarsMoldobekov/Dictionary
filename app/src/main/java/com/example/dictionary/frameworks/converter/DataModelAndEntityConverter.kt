package com.example.dictionary.frameworks.converter

import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.HistoryEntity
import com.example.dictionary.entities.Word

class DataModelAndEntityConverter : Converter {
    override fun mapEntityToDataModel(all: List<HistoryEntity>): List<Word> {
        val dataModel = ArrayList<Word>()
        if (!all.isNullOrEmpty()) {
            for (entity in all) {
                dataModel.add(Word(entity.id, entity.word, null))
            }
        }
        return dataModel
    }

    //TODO: convert List<Word> to List<HistoryEntity>
    override fun convertDataModelToEntity(appState: AppState): HistoryEntity? {
        return when (appState) {
            is AppState.Success -> {
                val searchResult = appState.data
                if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                    null
                } else {
                    HistoryEntity(searchResult[0].id, searchResult[0].text!!, null)
                }
            }
            else -> null
        }
    }
}