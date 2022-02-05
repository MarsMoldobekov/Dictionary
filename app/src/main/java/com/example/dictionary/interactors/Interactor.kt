package com.example.dictionary.interactors

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.web.IAndroidNetworkStatus
import com.example.dictionary.interfaceadapters.repositories.IRepository
import io.reactivex.Observable

class Interactor(
    private val remoteRepository: IRepository<List<Word>>,
    private val localRepository: IRepository<List<Word>>,
    private val androidNetworkStatus: IAndroidNetworkStatus
) : IInteractor<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> {
        return if (androidNetworkStatus.isNetworkAvailable()) {
            remoteRepository
        } else {
            localRepository
        }.getData(word)
    }
}