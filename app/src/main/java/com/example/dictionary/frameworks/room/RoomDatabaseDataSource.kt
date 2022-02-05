package com.example.dictionary.frameworks.room

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.datasource.DataSource

class RoomDatabaseDataSource : DataSource<List<Word>> {
    override suspend fun getData(word: String): List<Word> {
        TODO("Not yet implemented")
    }
}