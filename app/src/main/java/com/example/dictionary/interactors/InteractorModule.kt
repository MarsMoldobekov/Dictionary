package com.example.dictionary.interactors

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.di.NAME_LOCAL
import com.example.dictionary.frameworks.di.NAME_REMOTE
import com.example.dictionary.interfaceadapters.repositories.IRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {
    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: IRepository<List<Word>>,
        @Named(NAME_LOCAL) repositoryLocal: IRepository<List<Word>>
    ): IInteractor<List<Word>> {
        return Interactor(repositoryRemote, repositoryLocal)
    }
}