package com.example.dictionary.interfaceadapters.repositories

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.datasource.DataSource

class Repository(private val dataSource: DataSource<List<Word>>) : IRepository<List<Word>> {
    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}