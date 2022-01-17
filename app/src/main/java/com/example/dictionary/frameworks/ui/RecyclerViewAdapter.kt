package com.example.dictionary.frameworks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.databinding.RowItemBinding
import com.example.dictionary.entities.Word

class RecyclerViewAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<Word>
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    fun setData(data: List<Word>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding get() = RowItemBinding.bind(itemView)

        fun bind(data: Word) {
            with(binding) {
                headerTextviewRecyclerItem.text = data.text
                descriptionTextviewRecyclerItem.text =
                    data.meanings?.get(0)?.translation?.translation
            }

            itemView.setOnClickListener { onListItemClickListener.onItemClick(data) }
        }
    }

    interface OnListItemClickListener {
        fun onItemClick(data: Word)
    }
}