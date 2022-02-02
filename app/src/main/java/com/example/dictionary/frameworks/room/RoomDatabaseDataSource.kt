package com.example.dictionary.frameworks.room

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.datasource.DataSource
import io.reactivex.Observable

class RoomDatabaseDataSource : DataSource<List<Word>> {
    override fun getData(word: String): Observable<List<Word>> {
        TODO("Not yet implemented")
    }
}