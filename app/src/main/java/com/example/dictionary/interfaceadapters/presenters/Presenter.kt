package com.example.dictionary.interfaceadapters.presenters

import com.example.dictionary.entities.AppState
import com.example.dictionary.frameworks.room.RoomDatabaseDataSource
import com.example.dictionary.frameworks.rx.SchedulerProvider
import com.example.dictionary.frameworks.ui.View
import com.example.dictionary.frameworks.web.WebDataSource
import com.example.dictionary.interactors.Interactor
import com.example.dictionary.interfaceadapters.repositories.Repository
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

class Presenter(
    private val interactor: Interactor = Interactor(
        Repository(WebDataSource()), Repository(RoomDatabaseDataSource())
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : MvpPresenter<View>(), IPresenter {

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { viewState.renderData(AppState.Loading(null)) }
                .subscribe(
                    { viewState.renderData(it) },
                    { viewState.renderData(AppState.Error(it)) })
        )
    }
}