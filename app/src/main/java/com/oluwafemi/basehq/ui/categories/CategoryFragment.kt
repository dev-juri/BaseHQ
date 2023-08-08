package com.oluwafemi.basehq.ui.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.oluwafemi.basehq.R
import com.oluwafemi.basehq.adapter.CategoriesAdapter
import com.oluwafemi.basehq.databinding.FragmentCategoryBinding
import com.oluwafemi.basehq.ui.MainViewModel
import com.oluwafemi.basehq.utils.BottomAppTopLevelFragment
import com.oluwafemi.basehq.utils.viewBinding

class CategoryFragment : BottomAppTopLevelFragment(R.layout.fragment_category) {

    private val binding by viewBinding(FragmentCategoryBinding::bind)
    private lateinit var adapter: CategoriesAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CategoriesAdapter()
        binding.categoriesRecycler.adapter = adapter

        viewModel.categories.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }
}