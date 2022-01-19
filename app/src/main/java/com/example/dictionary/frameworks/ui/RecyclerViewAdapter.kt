package com.example.dictionary.frameworks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.RowItemBinding
import com.example.dictionary.interfaceadapters.presenters.IWordsListPresenter

class RecyclerViewAdapter(
    private val presenter: IWordsListPresenter
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply { itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) } }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class ViewHolder(private val viewBinding: RowItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root), IWordItemView {

        override var pos: Int = -1

        override fun setHeader(header: String) {
            viewBinding.headerTextviewRecyclerItem.text = header
        }

        override fun setDescription(description: String) {
            viewBinding.descriptionTextviewRecyclerItem.text = description
        }
    }
}