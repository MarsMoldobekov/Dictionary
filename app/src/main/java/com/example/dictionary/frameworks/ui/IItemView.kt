package com.example.dictionary.frameworks.ui

interface IItemView {
    var pos: Int
}

interface IWordItemView : IItemView {
    fun setHeader(header: String)
    fun setDescription(description: String)
}