package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.example.dictionary.entities.Word
import com.example.dictionary.interactors.IInteractor

class HistoryViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val interactor: IInteractor<List<Word>>
) : BaseViewModel() {

    override fun getData(word: String) {
        TODO("Not yet implemented")
    }

    override fun handleThrowable(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}