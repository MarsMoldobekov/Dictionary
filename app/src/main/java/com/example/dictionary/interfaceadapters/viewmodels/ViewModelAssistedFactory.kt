package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.SavedStateHandle

interface ViewModelAssistedFactory<V : BaseViewModel> {
    fun create(savedStateHandle: SavedStateHandle): V
}