package com.example.dictionary.interfaceadapters.repositories

interface IRepository<T> {
    suspend fun getData(word: String): T
}