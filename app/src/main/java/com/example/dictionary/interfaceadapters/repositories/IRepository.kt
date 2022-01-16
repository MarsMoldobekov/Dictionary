package com.example.dictionary.interfaceadapters.repositories

import io.reactivex.Observable

interface IRepository<T> {
    fun getData(word: String): Observable<T>
}