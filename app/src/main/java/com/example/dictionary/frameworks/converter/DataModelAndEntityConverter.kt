package com.example.dictionary.frameworks.converter

import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.HistoryEntity
import com.example.dictionary.entities.Meanings
import com.example.dictionary.entities.Word

class DataModelAndEntityConverter : Converter {
    override fun parseOnlineSearchResults(appState: AppState): AppState {
        return AppState.Success(mapResult(appState, true))
    }

    override fun parseLocalSearchResults(appState: AppState): AppState {
        return AppState.Success(mapResult(appState, false))
    }

    private fun mapResult(
        appState: AppState,
        isOnline: Boolean
    ): List<Word> {
        val newSearchResults = arrayListOf<Word>()
        when (appState) {
            is AppState.Success -> {
                getSuccessResultData(appState, isOnline, newSearchResults)
            }
        }
        return newSearchResults
    }

    private fun getSuccessResultData(appState: AppState.Success, isOnline: Boolean, newWords: ArrayList<Word>) {
        val words: List<Word> = appState.data as List<Word>
        if (words.isNotEmpty()) {
            if (isOnline) {
                for (searchResult in words) {
                    parseOnlineResult(searchResult, newWords)
                }
            } else {
                for (searchResult in words) {
                    newWords.add(Word(searchResult.id, searchResult.text, arrayListOf()))
                }
            }
        }
    }

    private fun parseOnlineResult(word: Word, newWords: ArrayList<Word>) {
        if (!word.text.isNullOrBlank() && !word.meanings.isNullOrEmpty()) {
            val newMeanings = arrayListOf<Meanings>()
            for (meaning in word.meanings) {
                if (meaning.translation != null &&
                    !meaning.translation.translation.isNullOrBlank()
                ) {
                    newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
                }
            }
            if (newMeanings.isNotEmpty()) {
                newWords.add(Word(word.id, word.text, newMeanings))
            }
        }
    }

    override fun mapEntityToDataModel(all: List<HistoryEntity>): List<Word> {
        val word = ArrayList<Word>()
        if (!all.isNullOrEmpty()) {
            for (entity in all) {
                word.add(Word(entity.id, entity.word, null))
            }
        }
        return word
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

    override fun convertMeaningsToString(meanings: List<Meanings>): String {
        var meaningsSeparatedByComma = String()
        for ((index, meaning) in meanings.withIndex()) {
            meaningsSeparatedByComma += if (index + 1 != meanings.size) {
                String.format("%s%s", meaning.translation?.translation, ", ")
            } else {
                meaning.translation?.translation
            }
        }
        return meaningsSeparatedByComma
    }
}