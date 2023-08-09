package com.oluwafemi.basehq.ui.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.oluwafemi.basehq.R
import com.oluwafemi.basehq.adapter.CategoriesAdapter
import com.oluwafemi.basehq.adapter.RecyclerItemClickListener
import com.oluwafemi.basehq.databinding.FragmentCategoryBinding
import com.oluwafemi.basehq.ui.SharedViewModel
import com.oluwafemi.basehq.ui.State
import com.oluwafemi.basehq.utils.BottomAppTopLevelFragment
import com.oluwafemi.basehq.utils.Category
import com.oluwafemi.basehq.utils.isOnline
import com.oluwafemi.basehq.utils.setGone
import com.oluwafemi.basehq.utils.setVisible
import com.oluwafemi.basehq.utils.viewBinding

class CategoryFragment : BottomAppTopLevelFragment(R.layout.fragment_category),
    RecyclerItemClickListener {

    private val binding by viewBinding(FragmentCategoryBinding::bind)
    private lateinit var adapter: CategoriesAdapter
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchRemoteData()
        observeData()

        adapter = CategoriesAdapter(this)
        binding.categoriesRecycler.adapter = adapter

    }

    private fun observeData() {
        viewModel.categories.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.state.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    State.SUCCESS -> binding.progressBar.setGone()
                    State.LOADING -> binding.progressBar.setVisible()
                    State.ERROR -> {

                        val snackBar = Snackbar.make(
                            binding.root,
                            if (isOnline(requireContext())) "Something went wrong, please try again" else "No InternetConnection",
                            Snackbar.LENGTH_INDEFINITE
                        )
                        if (isOnline(requireContext())) {
                            snackBar.setAction("Try Again") { fetchRemoteData() }.show()
                        } else {
                            snackBar.show()
                        }

                        binding.progressBar.setGone()
                    }
                }
            }
        }

    }

    private fun fetchRemoteData() {
        viewModel.fetchCategories()
        viewModel.fetchProducts()
    }

    override fun onClick(key: String) {
        viewModel.fetchProductsWithCategory(Category.fromValue(key))
        findNavController().navigate(R.id.action_categoryFragment_to_productsFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.resetState()
    }
}