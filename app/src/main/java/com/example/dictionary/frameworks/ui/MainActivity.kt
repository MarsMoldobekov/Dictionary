package com.example.dictionary.frameworks.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.di.SCOPE_MAIN_ACTIVITY
import com.example.dictionary.frameworks.utils.convertMeaningsToString
import com.example.dictionary.frameworks.utils.viewById
import com.example.dictionary.interactors.Interactor
import com.example.dictionary.interfaceadapters.viewmodels.MainViewModel
import com.example.dictionary.interfaceadapters.viewmodels.MainViewModelFactory
import com.example.dictionary.interfaceadapters.viewmodels.SavedStateViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class MainActivity : BaseActivity<AppState, Interactor>() {
    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

    private val recyclerView by viewById<RecyclerView>(R.id.main_activity_recyclerview)
    private val searchFAB by viewById<FloatingActionButton>(R.id.search_fab)

    private val scopeMainActivity = getKoin().createScope(SCOPE_MAIN_ACTIVITY, named(SCOPE_MAIN_ACTIVITY))

    private val mainViewModelFactory = scopeMainActivity.get<MainViewModelFactory>()
    override val viewModel: MainViewModel by viewModels {
        SavedStateViewModelFactory(mainViewModelFactory, this)
    }

    private val adapter: RecyclerViewAdapter by lazy { RecyclerViewAdapter(::onItemClick) }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(wordToSearch: String) {
                viewModel.getData(wordToSearch, androidNetworkStatus.isNetworkAvailable())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        searchFAB.setOnClickListener {
            SearchDialogFragment.newInstance().apply {
                this.setOnSearchClickListener(onSearchClickListener)
            }.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adapter
        }

        viewModel.getLiveData().observe(this) { renderData(it) }
    }

    override fun onDestroy() {
        scopeMainActivity.close()
        super.onDestroy()
    }

    fun onItemClick(word: Word) {
        startActivity(
            DescriptionActivity.getIntent(
                context = this@MainActivity,
                word = word.text!!,
                description = convertMeaningsToString(word.meanings!!),
                url = word.meanings[0].imageUrl
            )
        )
    }

    override fun setDataToAdapter(data: List<Word>) {
        adapter.submitList(data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
