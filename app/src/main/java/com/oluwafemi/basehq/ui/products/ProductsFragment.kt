package com.oluwafemi.basehq.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.oluwafemi.basehq.R
import com.oluwafemi.basehq.adapter.ProductsAdapter
import com.oluwafemi.basehq.databinding.FragmentProductsBinding
import com.oluwafemi.basehq.ui.MainViewModel
import com.oluwafemi.basehq.utils.BaseFragment
import com.oluwafemi.basehq.utils.viewBinding

class ProductsFragment : BaseFragment(R.layout.fragment_products) {

    private val binding by viewBinding(FragmentProductsBinding::bind)
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: ProductsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductsAdapter(requireContext())
        binding.productsRecycler.apply {
            adapter = adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}