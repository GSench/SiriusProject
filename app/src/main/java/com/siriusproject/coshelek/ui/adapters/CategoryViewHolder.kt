package com.siriusproject.coshelek.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.siriusproject.coshelek.databinding.CategoryItemBinding
import com.siriusproject.coshelek.wallet_information.data.model.CategoryUiModel

class CategoryViewHolder(private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CategoryUiModel, selected: Boolean) {
        with(binding){
            catTitle.text = item.name
            catImg.setImageDrawable(item.picture)
            select(selected)
        }
    }
    fun select(selected: Boolean){
        binding.selectedCatIcon.visibility = if(selected) View.VISIBLE else View.GONE
    }
}