package com.example.taskn9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskn9.databinding.CategoryItemRecyclerviewBinding

class CategoryRecyclerViewAdapter :
    ListAdapter<CategoryType, CategoryRecyclerViewAdapter.CategoryViewHolder>(
        object : DiffUtil.ItemCallback<CategoryType>() {

            override fun areItemsTheSame(oldItem: CategoryType, newItem: CategoryType): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: CategoryType, newItem: CategoryType): Boolean {
                return oldItem == newItem
            }
        }) {

    val listOfNames = listOf(
        "All",
        "\uD83C\uDF89 Party",
        "\uD83C\uDFD5️ Camping",
        "\uD83D\uDD74️ Classic",
        "\uD83E\uDDE5 Hoodies",
        "\uD83D\uDC5A Casual"
    )
    var prevButton: AppCompatButton? = null
    var onItemClick: ((AppCompatButton, CategoryType) -> Unit)? = null

    fun setData(categories: List<CategoryType>) {
        submitList(categories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind()
        holder.setSelectedButton()
        holder.onButtonClick()
    }

    inner class CategoryViewHolder(private val binding: CategoryItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.filterBtn.text = listOfNames[adapterPosition]
        }

        fun setSelectedButton() {
            if (adapterPosition == 0) {
                prevButton = binding.filterBtn
                binding.filterBtn.setBackgroundResource(R.drawable.selected_category_background)
                binding.filterBtn.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }
        }

        fun onButtonClick() {
            binding.filterBtn.setOnClickListener {
                prevButton?.setBackgroundResource(R.drawable.unselected_category_background)
                prevButton?.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.light_grey
                    )
                )
                prevButton = binding.filterBtn
                onItemClick?.invoke(binding.filterBtn, currentList[adapterPosition])
            }
        }
    }
}