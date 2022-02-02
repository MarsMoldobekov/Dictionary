package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.ViewModel
import com.example.dictionary.frameworks.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}