package com.example.dictionary.interactors

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.di.NAME_LOCAL
import com.example.dictionary.frameworks.di.NAME_REMOTE
import com.example.dictionary.interfaceadapters.repositories.IRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class Interactor @Inject constructor(
    @Named(NAME_REMOTE) private val remoteRepository: IRepository<List<Word>>,
    @Named(NAME_LOCAL) private val localRepository: IRepository<List<Word>>,
) : IInteractor<List<Word>> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<List<Word>> {
        return if (fromRemoteSource) { remoteRepository } else { localRepository }.getData(word)
    }
}