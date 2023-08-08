package com.oluwafemi.basehq.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oluwafemi.basehq.data.repository.Repository
import com.oluwafemi.basehq.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    init {
        fetchCategories()
        fetchProducts()
    }

    val products = repository.fetchSavedProducts()
    val categories = repository.fetchSavedCategories()

    fun fetchCategories() {
        viewModelScope.launch {
            when (repository.fetchRemoteCategories()) {
                is NetworkState.Success -> {}
                else -> {}
            }
        }
    }

    fun fetchProducts() {
        viewModelScope.launch {
            when (repository.fetchRemoteProducts()) {
                is NetworkState.Success -> {}
                else -> {}
            }
        }
    }

}