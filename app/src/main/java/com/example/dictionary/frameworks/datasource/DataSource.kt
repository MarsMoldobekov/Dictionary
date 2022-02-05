package com.example.dictionary.frameworks.datasource

interface DataSource<T> {
    suspend fun getData(word: String): T
}