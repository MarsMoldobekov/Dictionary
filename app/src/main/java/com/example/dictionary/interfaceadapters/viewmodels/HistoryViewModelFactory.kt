package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.example.dictionary.frameworks.converter.Converter
import com.example.dictionary.interactors.HistoryInteractor

class HistoryViewModelFactory(
    private val interactor: HistoryInteractor,
    private val converter: Converter
) : ViewModelAssistedFactory<HistoryViewModel> {

    override fun create(savedStateHandle: SavedStateHandle): HistoryViewModel {
        return HistoryViewModel(savedStateHandle, interactor, converter)
    }
}