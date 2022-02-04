package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.rx.ICompositeDisposableProvider
import com.example.dictionary.frameworks.rx.ISchedulerProvider
import com.example.dictionary.interactors.IInteractor

class MainViewModel(
    private val interactor: IInteractor<List<Word>>,
    private val schedulerProvider: ISchedulerProvider,
    private val compositeDisposableProvider: ICompositeDisposableProvider,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val LIVE_DATA_TAG = "LIVE_DATA_TAG"
    }

    fun getData(word: String) {
        compositeDisposableProvider.add(
            interactor.getData(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { savedStateHandle[LIVE_DATA_TAG] = AppState.Loading(null) }
                .subscribe(
                    { savedStateHandle[LIVE_DATA_TAG] = AppState.Success(it) },
                    { savedStateHandle[LIVE_DATA_TAG] = AppState.Error(it) }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposableProvider.clear()
    }

    fun getLiveData(): LiveData<AppState> {
        return savedStateHandle.getLiveData(LIVE_DATA_TAG)
    }
}