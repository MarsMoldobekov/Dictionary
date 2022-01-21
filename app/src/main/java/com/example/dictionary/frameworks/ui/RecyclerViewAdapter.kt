package com.example.dictionary.frameworks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.RowItemBinding
import com.example.dictionary.entities.Word

class RecyclerViewAdapter(
    private val onListItemClickListener: OnListItemClickListener
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var data: MutableList<Word> = mutableListOf()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(WordsDiffUtilCallback(field, value))
            with(field) { clear(); addAll(value) }
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnListItemClickListener {
        fun onItemClick(data: Word)
    }

    inner class ViewHolder(private val viewBinding: RowItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: Word) {
            with(viewBinding) {
                headerTextviewRecyclerItem.text = data.text
                descriptionTextviewRecyclerItem.text = data.meanings?.get(0)?.translation?.translation
                root.setOnClickListener { onListItemClickListener.onItemClick(data) }
            }
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
}