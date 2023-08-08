package com.oluwafemi.basehq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oluwafemi.basehq.data.domain.DomainCategory
import com.oluwafemi.basehq.databinding.CategoryListItemBinding
import com.oluwafemi.basehq.utils.Category

class CategoriesAdapter :
    ListAdapter<DomainCategory, CategoriesAdapter.CategoryViewHolder>(CategoryDiffUtilCallback) {

    class CategoryViewHolder constructor(private var binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: DomainCategory) {
            binding.category.text = Category.toValue(category.category)
        }

        companion object {
            fun from(parent: ViewGroup): CategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CategoryListItemBinding.inflate(layoutInflater, parent, false)
                return CategoryViewHolder(binding)
            }
        }
    }

    companion object CategoryDiffUtilCallback : DiffUtil.ItemCallback<DomainCategory>() {
        override fun areItemsTheSame(oldItem: DomainCategory, newItem: DomainCategory): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DomainCategory, newItem: DomainCategory): Boolean {
            return oldItem.category == newItem.category
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }
}