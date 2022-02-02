package com.example.dictionary.interfaceadapters.repositories

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.datasource.DataSource
import io.reactivex.Observable

class Repository(private val dataSource: DataSource<List<Word>>) : IRepository<List<Word>> {
    override fun getData(word: String): Observable<List<Word>> {
        return dataSource.getData(word)
    }
}