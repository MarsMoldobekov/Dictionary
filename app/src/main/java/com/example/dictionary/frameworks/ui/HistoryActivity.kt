package com.example.dictionary.frameworks.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.dictionary.databinding.ActivityHistoryBinding
import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.interactors.HistoryInteractor
import com.example.dictionary.interfaceadapters.viewmodels.HistoryViewModel
import com.example.dictionary.interfaceadapters.viewmodels.HistoryViewModelFactory
import com.example.dictionary.interfaceadapters.viewmodels.SavedStateViewModelFactory
import org.koin.android.ext.android.inject

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {
    private val historyViewModelFactory by inject<HistoryViewModelFactory>()
    override val viewModel by viewModels<HistoryViewModel> {
        SavedStateViewModelFactory(historyViewModelFactory, this)
    }
    private lateinit var activityHistoryBinding: ActivityHistoryBinding
    private val adapter by lazy { HistoryAdapter(::onItemClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHistoryBinding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(activityHistoryBinding.root)

        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        if (activityHistoryBinding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        viewModel.subscribe().observe(this@HistoryActivity) { renderData(it) }
    }

    private fun initViews() {
        activityHistoryBinding.historyActivityRecyclerview.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData("", androidNetworkStatus.isNetworkAvailable())
    }

    override fun setDataToAdapter(data: List<Word>) {
        adapter.data = data as MutableList<Word>
    }

    fun onItemClick(word: Word) {
        Toast.makeText(this, "on click: ${word.text}", Toast.LENGTH_SHORT).show()
    }
}