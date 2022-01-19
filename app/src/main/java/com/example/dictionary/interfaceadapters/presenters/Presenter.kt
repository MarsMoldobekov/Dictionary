package com.example.dictionary.interfaceadapters.presenters

import androidx.recyclerview.widget.DiffUtil
import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.room.RoomDatabaseDataSource
import com.example.dictionary.frameworks.rx.SchedulerProvider
import com.example.dictionary.frameworks.ui.IWordItemView
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

    class WordsListPresenter : IWordsListPresenter {
        private val words = mutableListOf<Word>()

        override var itemClickListener: ((IWordItemView) -> Unit)? = null

        override fun bindView(view: IWordItemView) {
            with(words[view.pos]) {
                text?.let { view.setHeader(it) }
                meanings?.get(0)?.translation?.translation?.let { view.setDescription(it) }
            }
        }

        override fun getCount(): Int {
            return words.size
        }

        fun addWords(listOfWords: List<Word>): DiffUtil.DiffResult {
            val diffResult = DiffUtil.calculateDiff(WordsDiffUtilCallback(words, listOfWords))
            with(words) { clear(); addAll(listOfWords) }
            return diffResult
        }

        fun getByPosition(position: Int): Word {
            return words[position]
        }
    }

    class WordsDiffUtilCallback(
        private val oldList: List<Word>,
        private val newList: List<Word>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    val wordsListPresenter = WordsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        wordsListPresenter.itemClickListener = {
            viewState.showToast(wordsListPresenter.getByPosition(it.pos))
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { viewState.renderData(AppState.Loading(null)) }
                .subscribe({
                    viewState.renderData(AppState.Success(it))
                    viewState.updateList(wordsListPresenter.addWords(it))
                }, { viewState.renderData(AppState.Error(it)) })
        )
    }
}