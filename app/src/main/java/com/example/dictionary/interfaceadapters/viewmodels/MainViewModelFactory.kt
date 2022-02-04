package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.rx.ICompositeDisposableProvider
import com.example.dictionary.frameworks.rx.ISchedulerProvider
import com.example.dictionary.interactors.IInteractor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModelFactory @Inject constructor(
    private val interactor: IInteractor<List<Word>>,
    private val schedulerProvider: ISchedulerProvider,
    private val compositeDisposableProvider: ICompositeDisposableProvider
) : ViewModelAssistedFactory<MainViewModel> {

    override fun create(savedStateHandle: SavedStateHandle): MainViewModel {
        return MainViewModel(interactor, schedulerProvider, compositeDisposableProvider, savedStateHandle)
    }
}