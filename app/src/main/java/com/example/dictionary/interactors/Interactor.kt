package com.example.dictionary.interactors

import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.interfaceadapters.repositories.IRepository
import com.example.dictionary.interfaceadapters.repositories.IRepositoryLocal

class Interactor(
    private val remoteRepository: IRepository<List<Word>>,
    private val localRepository: IRepositoryLocal<List<Word>>,
) : IInteractor<AppState> {

    override suspend fun getData(word: String, isOnline: Boolean): AppState {
        val appState: AppState
        if (isOnline) {
            appState = AppState.Success(remoteRepository.getData(word))
            localRepository.saveToDatabase(appState)
        } else {
            appState = AppState.Success(localRepository.getData(word))
        }
        return appState
    }
}