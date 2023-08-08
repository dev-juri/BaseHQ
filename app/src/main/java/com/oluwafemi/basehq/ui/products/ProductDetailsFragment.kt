package com.oluwafemi.basehq.ui.products

import androidx.fragment.app.activityViewModels
import com.oluwafemi.basehq.R
import com.oluwafemi.basehq.databinding.FragmentProductDetailsBinding
import com.oluwafemi.basehq.ui.MainViewModel
import com.oluwafemi.basehq.utils.BaseFragment
import com.oluwafemi.basehq.utils.viewBinding

class ProductDetailsFragment : BaseFragment(R.layout.fragment_product_details) {

    private val binding by viewBinding(FragmentProductDetailsBinding::bind)
    private val viewModel: MainViewModel by activityViewModels()
}