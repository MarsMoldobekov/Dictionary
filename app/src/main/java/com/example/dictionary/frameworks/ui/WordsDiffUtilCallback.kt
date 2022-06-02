package com.example.dictionary.frameworks.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.dictionary.entities.Word

class WordsDiffUtilCallback : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem == newItem
}