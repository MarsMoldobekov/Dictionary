package com.example.dictionary.frameworks.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface View : MvpView {
    fun renderData(appState: AppState)
    fun init()
    fun showToast(data: Word)
    fun updateList(diffResult: DiffUtil.DiffResult)
}