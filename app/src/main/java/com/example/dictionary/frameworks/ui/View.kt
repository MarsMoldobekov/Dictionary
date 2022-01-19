package com.example.dictionary.frameworks.ui

import com.example.dictionary.entities.AppState
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface View : MvpView {
    @AddToEndSingle
    fun renderData(appState: AppState)
}