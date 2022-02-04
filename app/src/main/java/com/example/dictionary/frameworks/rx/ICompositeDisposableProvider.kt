package com.example.dictionary.frameworks.rx

import io.reactivex.disposables.Disposable

interface ICompositeDisposableProvider {
    fun add(disposable: Disposable): Boolean
    fun clear()
}