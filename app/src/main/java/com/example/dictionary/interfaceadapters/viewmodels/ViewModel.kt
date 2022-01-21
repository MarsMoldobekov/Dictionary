package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dictionary.entities.AppState
import com.example.dictionary.frameworks.room.RoomDatabaseDataSource
import com.example.dictionary.frameworks.web.WebDataSource
import com.example.dictionary.interactors.Interactor
import com.example.dictionary.interfaceadapters.repositories.Repository

class ViewModel(
    private val interactor: Interactor = Interactor(
        Repository(WebDataSource()), Repository(RoomDatabaseDataSource())
    )
) : BaseViewModel() {

    private val liveData = MutableLiveData<AppState>()

    fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
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