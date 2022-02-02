package com.example.dictionary.interactors

import com.example.dictionary.entities.Word
import com.example.dictionary.interfaceadapters.repositories.IRepository
import io.reactivex.Observable

class Interactor(
    private val remoteRepository: IRepository<List<Word>>,
    private val localRepository: IRepository<List<Word>>,
) : IInteractor<List<Word>> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<List<Word>> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word)
        } else {
            localRepository.getData(word)
        }
    }
}