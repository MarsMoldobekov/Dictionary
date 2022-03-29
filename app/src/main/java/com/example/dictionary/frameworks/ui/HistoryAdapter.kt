package com.example.dictionary.frameworks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.HistoryItemBinding
import com.example.dictionary.entities.Word

class HistoryAdapter(
    private val onItemClickListener: (Word) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {
    var data: MutableList<Word> = mutableListOf()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(WordsDiffUtilCallback(field, value))
            with(field) { clear(); addAll(value) }
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            HistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val viewBinding: HistoryItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(word: Word) {
            viewBinding.headerHistoryTextviewRecyclerItem.text = word.text
            itemView.setOnClickListener { onItemClickListener(word) }
        }
    }
}