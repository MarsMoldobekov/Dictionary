package com.example.dictionary.frameworks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.entities.AppState
import com.example.dictionary.interfaceadapters.presenters.IPresenter

abstract class BaseActivity<T: AppState> : AppCompatActivity(), View {
    protected lateinit var presenter: IPresenter<T, View>

    protected abstract fun createPresenter(): IPresenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}