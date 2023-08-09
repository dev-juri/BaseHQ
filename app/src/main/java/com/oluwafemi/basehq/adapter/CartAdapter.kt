package com.oluwafemi.basehq.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oluwafemi.basehq.data.domain.DomainCart
import com.oluwafemi.basehq.databinding.CartItemBinding

class CartAdapter(
    private val clickListener: RecyclerItemClickListener,
    private val context: Context
) :
    ListAdapter<DomainCart, CartAdapter.CartViewHolder>(CartDiffUtilCallback) {

    class CartViewHolder constructor(val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: DomainCart, context: Context) {
            binding.productName.text = cartItem.productName
            binding.productPrice.text = "$${cartItem.priceOfProduct}"
            binding.productQtd.text = "Qtd: ${cartItem.quantity}"
            Glide.with(context).load(cartItem.image).centerCrop().into(binding.img)
        }

        companion object {
            fun from(parent: ViewGroup): CartViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartItemBinding.inflate(layoutInflater, parent, false)
                return CartViewHolder(binding)
            }
        }
    }

    companion object CartDiffUtilCallback : DiffUtil.ItemCallback<DomainCart>() {
        override fun areItemsTheSame(oldItem: DomainCart, newItem: DomainCart): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DomainCart, newItem: DomainCart): Boolean {
            return oldItem.productId == newItem.productId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem, context)

        holder.binding.deleteFromCart.setOnClickListener {
            clickListener.onClick(cartItem.productId.toString())
        }
    }
}