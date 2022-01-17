package com.example.dictionary.frameworks.ui

import com.example.dictionary.entities.AppState

interface View {
    fun renderData(appState: AppState)
}