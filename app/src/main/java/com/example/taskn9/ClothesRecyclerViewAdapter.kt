package com.example.taskn9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskn9.databinding.ClothesItemRecyclerviewBinding

class ClothesRecyclerViewAdapter :
    ListAdapter<Clothes, ClothesRecyclerViewAdapter.ClothesViewHolder>(
        object : DiffUtil.ItemCallback<Clothes>() {

            override fun areItemsTheSame(oldItem: Clothes, newItem: Clothes): Boolean {
                return oldItem.image == newItem.image
            }

            override fun areContentsTheSame(oldItem: Clothes, newItem: Clothes): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    fun setData(clothes: MutableList<Clothes>) {
        submitList(clothes)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesViewHolder {
        return ClothesViewHolder(
            ClothesItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ClothesViewHolder, position: Int) {
        holder.bind()
    }

    inner class ClothesViewHolder(private val binding: ClothesItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val clothes = currentList[adapterPosition]
            with(binding) {
                clothesIv.setImageResource(clothes.image)
                clothesNameTv.text = clothes.title
                clothesPriceTv.text = clothes.price
            }
        }
    }


}