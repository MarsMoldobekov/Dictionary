package com.example.dictionary.interactors

import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.interfaceadapters.repositories.IRepository
import io.reactivex.Observable

class Interactor(
    private val remoteRepository: IRepository<List<Word>>,
    private val localRepository: IRepository<List<Word>>,
) : IInteractor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}