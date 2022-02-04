package com.example.dictionary.frameworks.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.dictionary.entities.AppState

abstract class BaseActivity : AppCompatActivity() {
    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }

    abstract val viewModel: ViewModel

    protected fun showAlertDialog(title: String, message: String) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    abstract fun renderData(appState: AppState)
}