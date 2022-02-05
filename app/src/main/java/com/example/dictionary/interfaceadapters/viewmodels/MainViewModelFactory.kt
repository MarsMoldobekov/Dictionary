package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.example.dictionary.entities.Word
import com.example.dictionary.interactors.IInteractor

class MainViewModelFactory(
    private val interactor: IInteractor<List<Word>>,
) : ViewModelAssistedFactory<MainViewModel> {

    override fun create(savedStateHandle: SavedStateHandle): MainViewModel {
        return MainViewModel(savedStateHandle, interactor)
    }
}