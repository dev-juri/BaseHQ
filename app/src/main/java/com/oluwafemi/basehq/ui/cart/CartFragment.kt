package com.oluwafemi.basehq.ui.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.oluwafemi.basehq.R
import com.oluwafemi.basehq.adapter.CartAdapter
import com.oluwafemi.basehq.adapter.RecyclerItemClickListener
import com.oluwafemi.basehq.databinding.FragmentCartBinding
import com.oluwafemi.basehq.ui.SharedViewModel
import com.oluwafemi.basehq.utils.BaseFragment
import com.oluwafemi.basehq.utils.setGone
import com.oluwafemi.basehq.utils.setVisible
import com.oluwafemi.basehq.utils.viewBinding

class CartFragment : BaseFragment(R.layout.fragment_cart), RecyclerItemClickListener {

    private val viewModel: SharedViewModel by activityViewModels()
    private val binding: FragmentCartBinding by viewBinding(FragmentCartBinding::bind)
    private lateinit var adapter: CartAdapter
    private var total = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CartAdapter(this, requireContext())
        binding.cartRecycler.adapter = adapter

        viewModel.cart.observe(viewLifecycleOwner) { cartItem ->
            if (cartItem.isNotEmpty()) {
                adapter.submitList(cartItem)
                binding.emptyStateText.setGone()
                total = 0.0
                cartItem.forEach {
                    total += it.priceOfProduct * it.quantity
                }
                binding.total.text = "Total = $${total}"
                binding.total.setVisible()
            } else {
                total = 0.0
                binding.total.setGone()
                binding.emptyStateText.setVisible()
                adapter.submitList(emptyList())
            }
        }
    }

    override fun onClick(key: String) {
        viewModel.deleteItemWithId(key.toLong())
    }
}