package com.oluwafemi.basehq.ui.products

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.oluwafemi.basehq.R
import com.oluwafemi.basehq.data.domain.DomainCart
import com.oluwafemi.basehq.data.domain.DomainProduct
import com.oluwafemi.basehq.databinding.FragmentProductDetailsBinding
import com.oluwafemi.basehq.ui.SharedViewModel
import com.oluwafemi.basehq.utils.BaseFragment
import com.oluwafemi.basehq.utils.viewBinding

class ProductDetailsFragment : BaseFragment(R.layout.fragment_product_details) {

    private val binding by viewBinding(FragmentProductDetailsBinding::bind)
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var item: DomainProduct

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedItem.observe(viewLifecycleOwner) {
            if (it != null) {
                item = it
                binding.productTitle.text = it.title
                binding.productPrice.text = "${it.price}"
                binding.productDesc.text = it.description
                Glide.with(requireContext()).load(it.image).centerCrop().into(binding.image)
                binding.ratingBar.rating = it.rating.toFloat()
            } else {
                findNavController().navigateUp()
            }
        }

        binding.addToCart.setOnClickListener {
            val qtd = binding.quantity.text.toString().trim()

            if (qtd.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Please fill in the quantity field",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val cart = DomainCart(
                    productId = item.id,
                    productName = item.title,
                    quantity = qtd.toLong(),
                    priceOfProduct = item.price
                )
                viewModel.addItemToCart(cart)
                Toast.makeText(
                    requireContext(),
                    "Item added to cart successfully, keep shopping",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigateUp()
            }
        }
    }

}