package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {
    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
            + SupervisorJob()
            + CoroutineExceptionHandler { _, throwable -> handleThrowable(throwable) }
    )

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    abstract fun getData(word: String)

    abstract fun handleThrowable(throwable: Throwable)
}