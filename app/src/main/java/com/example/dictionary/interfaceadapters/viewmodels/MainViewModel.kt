package com.example.dictionary.interfaceadapters.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.interactors.IInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val interactor: IInteractor<List<Word>>,
) : BaseViewModel() {

    companion object {
        private const val LIVE_DATA_TAG = "LIVE_DATA_TAG"
    }

    override fun getData(word: String, isOnline: Boolean) {
        savedStateHandle[LIVE_DATA_TAG] = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { searchAsync(word, isOnline) }
    }

    private suspend fun searchAsync(word: String, isOnline: Boolean) {
        var result: List<Word>?
        withContext(Dispatchers.IO) {
            result = interactor.getData(word, isOnline)
        }
        savedStateHandle[LIVE_DATA_TAG] = AppState.Success(result)
    }

    override fun onCleared() {
        savedStateHandle[LIVE_DATA_TAG] = null
        super.onCleared()
    }

    override fun handleThrowable(throwable: Throwable) {
        savedStateHandle[LIVE_DATA_TAG] = AppState.Error(throwable)
    }

    fun getLiveData(): LiveData<AppState> {
        return savedStateHandle.getLiveData(LIVE_DATA_TAG)
    }
}