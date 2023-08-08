package com.oluwafemi.basehq.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.oluwafemi.basehq.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.categories.observe(this) {
            if (it.isNotEmpty()) {
                binding.helloText.text = "${binding.helloText.text} \n\n $it \n\n"
            }
        }

        viewModel.products.observe(this) {
            if (it.isNotEmpty()) {
                binding.helloText.text = "${binding.helloText.text} \n\n $it \n\n"
            }
        }
    }
}