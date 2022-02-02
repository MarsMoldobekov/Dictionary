package com.example.dictionary.interfaceadapters.presenters

import com.example.dictionary.frameworks.ui.IItemView
import com.example.dictionary.frameworks.ui.IWordItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IWordsListPresenter : IListPresenter<IWordItemView>