package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.dictionary.entities.AppState
import com.example.dictionary.frameworks.converter.Converter
import com.example.dictionary.interactors.HistoryInteractor
import kotlinx.coroutines.launch

//TODO: save data with SaveStateHandle
class HistoryViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val interactor: HistoryInteractor,
    private val converter: Converter
) : BaseViewModel() {

    private val liveDataForViewToObserve = MutableLiveData<AppState>()

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            liveDataForViewToObserve
                .postValue(converter.parseLocalSearchResults(interactor.getData(word, isOnline)))
        }
    }

    override fun handleThrowable(throwable: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(throwable))
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
    }
}