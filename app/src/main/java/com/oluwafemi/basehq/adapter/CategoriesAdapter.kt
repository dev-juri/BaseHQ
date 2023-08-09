package com.oluwafemi.basehq.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oluwafemi.basehq.R
import com.oluwafemi.basehq.data.domain.DomainCategory
import com.oluwafemi.basehq.databinding.CategoryListItemBinding
import com.oluwafemi.basehq.utils.Category

class CategoriesAdapter(
    private val context: Context,
    private val clickListener: RecyclerItemClickListener
) :
    ListAdapter<DomainCategory, CategoriesAdapter.CategoryViewHolder>(CategoryDiffUtilCallback) {

    class CategoryViewHolder constructor(val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: DomainCategory, context: Context) {
            binding.category.text = Category.toValue(category.category)
            when (category.category) {
                Category.Electronics -> {
                    binding.img.setImageResource(R.drawable.electricity)
                    binding.parent.setCardBackgroundColor(
                        ContextCompat.getColor(context, R.color.bg1)
                    )
                }

                Category.Jewelery -> {
                    binding.img.setImageResource(R.drawable.jewelry)
                    binding.parent.setCardBackgroundColor(
                        ContextCompat.getColor(context, R.color.bg2)
                    )
                }

                Category.MenClothing -> {
                    binding.img.setImageResource(R.drawable.man)
                    binding.parent.setCardBackgroundColor(
                        ContextCompat.getColor(context, R.color.bg3)
                    )
                }

                Category.WomenClothing -> {
                    binding.img.setImageResource(R.drawable.womanshopping)
                    binding.parent.setCardBackgroundColor(
                        ContextCompat.getColor(context, R.color.bg4)
                    )
                }
            }
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
        holder.bind(category, context)

        holder.binding.parent.setOnClickListener {
            clickListener.onClick(category.category.value)
        }
    }
}