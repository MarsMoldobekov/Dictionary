package com.example.dictionary.interactors

interface IInteractor<T> {
    suspend fun getData(word: String): T
}