package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.interactors.IInteractor
import javax.inject.Inject

//TODO: SavedStateHandle
class MainViewModel @Inject constructor(private val interactor: IInteractor<List<Word>>) : BaseViewModel() {

    private val liveData = MutableLiveData<AppState>()

    fun getData(word: String) {
        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { liveData.postValue(AppState.Loading(null)) }
                .subscribe(
                    { liveData.postValue(AppState.Success(it)) },
                    { liveData.postValue(AppState.Error(it)) }
                )
        )
    }

    fun getLiveData(): LiveData<AppState> {
        return liveData
    }
}