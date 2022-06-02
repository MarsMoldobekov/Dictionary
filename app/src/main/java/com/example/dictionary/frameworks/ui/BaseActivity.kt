package com.example.dictionary.frameworks.ui

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.R
import com.example.dictionary.databinding.LoadingLayoutBinding
import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.web.IAndroidNetworkStatus
import com.example.dictionary.interactors.IInteractor
import com.example.dictionary.interfaceadapters.viewmodels.BaseViewModel
import org.koin.android.ext.android.inject

abstract class BaseActivity<T : AppState, I : IInteractor<T>> : AppCompatActivity() {
    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }

    protected val androidNetworkStatus by inject<IAndroidNetworkStatus>()
    private lateinit var binding: LoadingLayoutBinding
    abstract val viewModel: BaseViewModel

    override fun onResume() {
        super.onResume()
        binding = LoadingLayoutBinding.inflate(layoutInflater)
        if (!androidNetworkStatus.isNetworkAvailable() && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    private fun showAlertDialog(title: String, message: String) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = View.VISIBLE
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    protected fun renderData(appState: T) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                appState.error.message?.let { showAlertDialog(getString(R.string.error_stub), it) }
            }
        }
    }

    abstract fun setDataToAdapter(data: List<Word>)
}