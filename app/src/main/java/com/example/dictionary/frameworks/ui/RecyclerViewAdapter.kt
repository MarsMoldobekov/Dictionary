package com.example.dictionary.frameworks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.RowItemBinding
import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.utils.convertMeaningsToString

class RecyclerViewAdapter(
    private val onListItemClickListener: (Word) -> Unit
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

    inner class ViewHolder(private val viewBinding: RowItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: Word) {
            with(viewBinding) {
                headerTextviewRecyclerItem.text = data.text
                descriptionTextviewRecyclerItem.text = convertMeaningsToString(data.meanings!!)
                root.setOnClickListener { onListItemClickListener(data) }
            }
        }
    }
}