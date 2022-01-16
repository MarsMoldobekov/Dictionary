package com.example.dictionary.interfaceadapters.presenters

import com.example.dictionary.entities.AppState
import com.example.dictionary.frameworks.room.RoomDatabaseDataSource
import com.example.dictionary.frameworks.rx.SchedulerProvider
import com.example.dictionary.frameworks.ui.View
import com.example.dictionary.frameworks.web.WebDataSource
import com.example.dictionary.interactors.Interactor
import com.example.dictionary.interfaceadapters.repositories.Repository
import io.reactivex.disposables.CompositeDisposable

class Presenter<T : AppState, V : View>(
    private val interactor: Interactor = Interactor(
        Repository(WebDataSource()), Repository(RoomDatabaseDataSource())
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : IPresenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (currentView != view) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribe({ currentView?.renderData(it) }, { currentView?.renderData(AppState.Error(it)) })
        )
    }
}