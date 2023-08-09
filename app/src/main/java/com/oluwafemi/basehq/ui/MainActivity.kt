package com.oluwafemi.basehq.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.oluwafemi.basehq.databinding.ActivityMainBinding
import com.oluwafemi.basehq.utils.BottomAppBar
import com.oluwafemi.basehq.utils.setGone
import com.oluwafemi.basehq.utils.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SharedViewModel by activityViewModels()

    private val fragmentLifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
            super.onFragmentResumed(fm, f)

            (f as? BottomAppBar)?.apply {
                if (f.showContent) {
                    binding.bottomAppBar.setVisible()
                    binding.goToCart.setVisible()
                } else {
                    binding.bottomAppBar.setGone()
                    binding.goToCart.setGone()
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        (this as FragmentActivity).supportFragmentManager.registerFragmentLifecycleCallbacks(
            fragmentLifecycleCallbacks,
            true
        )

        viewModel.cart.observe(this) {
            if (it.isNotEmpty()) {
            } else {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (this as FragmentActivity).supportFragmentManager.unregisterFragmentLifecycleCallbacks(
            fragmentLifecycleCallbacks
        )
    }
}