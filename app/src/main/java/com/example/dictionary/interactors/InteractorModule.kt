package com.example.dictionary.interactors

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.di.NAME_LOCAL
import com.example.dictionary.frameworks.di.NAME_REMOTE
import com.example.dictionary.frameworks.web.AndroidNetworkStatus
import com.example.dictionary.frameworks.web.AndroidNetworkStatusModule
import com.example.dictionary.interfaceadapters.repositories.IRepository
import com.example.dictionary.interfaceadapters.repositories.RepositoryModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [RepositoryModule::class, AndroidNetworkStatusModule::class])
class InteractorModule {
    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: IRepository<List<Word>>,
        @Named(NAME_LOCAL) repositoryLocal: IRepository<List<Word>>,
        androidNetworkStatus: AndroidNetworkStatus
    ): IInteractor<List<Word>> {
        return Interactor(repositoryRemote, repositoryLocal, androidNetworkStatus)
    }
}