package com.example.dictionary.interactors

import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.web.IAndroidNetworkStatus
import com.example.dictionary.interfaceadapters.repositories.IRepository
import com.example.dictionary.interfaceadapters.repositories.IRepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: IRepository<List<Word>>,
    private val repositoryLocal: IRepositoryLocal<List<Word>>,
    private val androidNetworkStatus: IAndroidNetworkStatus
) : IInteractor<AppState> {

    override suspend fun getData(word: String): AppState {
        return AppState.Success(
            if (androidNetworkStatus.isNetworkAvailable()) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}