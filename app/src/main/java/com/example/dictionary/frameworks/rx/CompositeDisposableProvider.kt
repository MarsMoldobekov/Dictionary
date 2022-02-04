package com.example.dictionary.frameworks.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class CompositeDisposableProvider : ICompositeDisposableProvider {
    private val compositeDisposable = CompositeDisposable()

    override fun add(disposable: Disposable): Boolean {
        return compositeDisposable.add(disposable)
    }

    override fun clear() {
        compositeDisposable.clear()
    }
}