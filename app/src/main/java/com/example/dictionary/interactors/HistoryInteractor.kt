package com.example.dictionary.interactors

import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.interfaceadapters.repositories.IRepository
import com.example.dictionary.interfaceadapters.repositories.IRepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: IRepository<List<Word>>,
    private val repositoryLocal: IRepositoryLocal<List<Word>>,
) : IInteractor<AppState> {

    override suspend fun getData(word: String, isOnline: Boolean): AppState {
        return AppState.Success(
            if (isOnline) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}