package com.example.dictionary.frameworks.datasource

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}