package com.example.dictionary.interfaceadapters.presenters

interface IPresenter {
    fun getData(word: String, isOnline: Boolean)
}