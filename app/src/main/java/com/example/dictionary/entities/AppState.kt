package com.example.dictionary.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class AppState : Parcelable {
    @Parcelize data class Success(val data: List<Word>?) : AppState()
    @Parcelize data class Error(val error: Throwable) : AppState()
    @Parcelize data class Loading(val progress: Int?) : AppState()
}