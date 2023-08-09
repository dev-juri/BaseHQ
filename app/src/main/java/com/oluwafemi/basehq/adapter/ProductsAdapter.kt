package com.oluwafemi.basehq.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oluwafemi.basehq.data.domain.DomainProduct
import com.oluwafemi.basehq.databinding.ProductListItemBinding

class ProductsAdapter(
    private val clickListener: RecyclerItemClickListener,
    private val context: Context
) :
    ListAdapter<DomainProduct, ProductsAdapter.ProductViewHolder>(ProductDiffUtilCallback) {

    class ProductViewHolder constructor(val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: DomainProduct, context: Context) {
            binding.productName.text = product.title

            Glide.with(context).load(product.image).centerCrop().into(binding.img)

            binding.ratingBar.rating = product.rating.toFloat()
        }

        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductListItemBinding.inflate(layoutInflater, parent, false)
                return ProductViewHolder(binding)
            }
        }
    }

    companion object ProductDiffUtilCallback : DiffUtil.ItemCallback<DomainProduct>() {
        override fun areItemsTheSame(oldItem: DomainProduct, newItem: DomainProduct): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DomainProduct, newItem: DomainProduct): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product, context)

        holder.binding.parent.setOnClickListener {
            clickListener.onClick(product.id.toString())
        }
    }
}