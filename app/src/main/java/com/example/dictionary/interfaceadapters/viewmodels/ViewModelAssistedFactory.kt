package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface ViewModelAssistedFactory<V : ViewModel> {
    fun create(savedStateHandle: SavedStateHandle): V
}