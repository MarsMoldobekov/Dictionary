package com.example.dictionary.frameworks.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.utils.convertMeaningsToString
import com.example.dictionary.interfaceadapters.viewmodels.MainViewModel
import com.example.dictionary.interfaceadapters.viewmodels.MainViewModelFactory
import com.example.dictionary.interfaceadapters.viewmodels.SavedStateViewModelFactory
import org.koin.android.ext.android.inject

//TODO: change UI word search
class MainActivity : BaseActivity() {
    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

    private lateinit var binding: ActivityMainBinding

    private val mainViewModelFactory: MainViewModelFactory by inject()
    override val viewModel: MainViewModel by viewModels {
        SavedStateViewModelFactory(mainViewModelFactory, this)
    }

    private val adapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter(object : RecyclerViewAdapter.OnListItemClickListener {
            override fun onItemClick(data: Word) {
                startActivity(
                    DescriptionActivity.getIntent(
                        context = this@MainActivity,
                        word = data.text!!,
                        description = convertMeaningsToString(data.meanings!!),
                        url = data.meanings[0].imageUrl
                    )
                )
            }
        })
    }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(wordToSearch: String) {
                viewModel.getData(wordToSearch)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchFab.setOnClickListener {
            SearchDialogFragment.newInstance().apply {
                this.setOnSearchClickListener(onSearchClickListener)
            }.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

        with(binding) {
            mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
            mainActivityRecyclerview.adapter = adapter
        }

        viewModel.getLiveData().observe(this) { renderData(it) }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data

                if (dataModel.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.dialog_tittle_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )
                } else {
                    adapter.data = appState.data as MutableList<Word>
                    binding.loadingFrameLayout.visibility = GONE
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                appState.error.message?.let { showAlertDialog(getString(R.string.error_stub), it) }
            }
        }
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = VISIBLE
    }
}
