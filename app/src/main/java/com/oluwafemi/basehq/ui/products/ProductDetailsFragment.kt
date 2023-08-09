package com.oluwafemi.basehq.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.oluwafemi.basehq.R
import com.oluwafemi.basehq.databinding.FragmentProductDetailsBinding
import com.oluwafemi.basehq.ui.SharedViewModel
import com.oluwafemi.basehq.utils.BaseFragment
import com.oluwafemi.basehq.utils.viewBinding

class ProductDetailsFragment : BaseFragment(R.layout.fragment_product_details) {

    private val binding by viewBinding(FragmentProductDetailsBinding::bind)
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedItem.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.productTitle.text = it.title
                binding.productDesc.text = it.description
                Glide.with(requireContext()).load(it.image).centerCrop().into(binding.image)
                binding.ratingBar.rating = it.rating.toFloat()
            } else {
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.resetSelectedItem()
    }
}