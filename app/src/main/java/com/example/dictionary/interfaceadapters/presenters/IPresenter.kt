package com.example.dictionary.interfaceadapters.presenters

import com.example.dictionary.entities.AppState
import com.example.dictionary.frameworks.ui.View

interface IPresenter<T: AppState, V: View> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}