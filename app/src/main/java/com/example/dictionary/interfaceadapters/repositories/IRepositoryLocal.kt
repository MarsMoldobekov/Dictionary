package com.example.dictionary.interfaceadapters.repositories

import com.example.dictionary.entities.AppState

interface IRepositoryLocal<T> : IRepository<T> {
    suspend fun saveToDatabase(appState: AppState)
}