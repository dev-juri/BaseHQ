package com.oluwafemi.basehq.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.oluwafemi.basehq.R
import com.oluwafemi.basehq.adapter.ProductsAdapter
import com.oluwafemi.basehq.adapter.RecyclerItemClickListener
import com.oluwafemi.basehq.databinding.FragmentProductsBinding
import com.oluwafemi.basehq.ui.SharedViewModel
import com.oluwafemi.basehq.utils.BaseFragment
import com.oluwafemi.basehq.utils.Category
import com.oluwafemi.basehq.utils.viewBinding

class ProductsFragment : BaseFragment(R.layout.fragment_products), RecyclerItemClickListener {

    private val binding by viewBinding(FragmentProductsBinding::bind)
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var adapter: ProductsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductsAdapter(this, requireContext())
        binding.productsRecycler.adapter = adapter

        viewModel.filteredProduct.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
                binding.category.text = Category.toValue(it[0].category)
            }
        }
    }

    override fun onClick(key: String) {
        val id = key.toLong()
        viewModel.fetchProductWithId(id)
        findNavController().navigate(R.id.action_productsFragment_to_productDetailsFragment)
    }

}