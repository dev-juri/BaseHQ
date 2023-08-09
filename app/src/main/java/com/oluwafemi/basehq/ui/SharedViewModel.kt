package com.oluwafemi.basehq.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oluwafemi.basehq.data.domain.DomainCart
import com.oluwafemi.basehq.data.domain.DomainProduct
import com.oluwafemi.basehq.data.repository.Repository
import com.oluwafemi.basehq.utils.Category
import com.oluwafemi.basehq.utils.NetworkState
import com.oluwafemi.basehq.utils.toDbModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _state = MutableLiveData<State?>()
    val state: LiveData<State?> get() = _state

    private var _selectedItem = MutableLiveData<DomainProduct?>()
    val selectedItem: LiveData<DomainProduct?> get() = _selectedItem

    private var _filteredProduct = MutableLiveData<List<DomainProduct>>()
    val filteredProduct: LiveData<List<DomainProduct>> get() = _filteredProduct

    val categories = repository.fetchSavedCategories()
    val cart = repository.fetchCartItems()

    init {
        fetchCategories()
        fetchProducts()
    }

    fun fetchCategories() {
        viewModelScope.launch {
            _state.value = State.LOADING
            when (repository.fetchRemoteCategories()) {
                is NetworkState.Success -> {
                    _state.value = State.SUCCESS
                }

                is NetworkState.Error -> {
                    _state.value = State.ERROR
                }

                else -> {
                    _state.value = State.LOADING
                }
            }
        }
    }

    fun fetchProducts() {
        viewModelScope.launch {
            _state.value = State.LOADING
            when (repository.fetchRemoteProducts()) {
                is NetworkState.Success -> {
                    _state.value = State.SUCCESS
                }

                is NetworkState.Error -> {
                    _state.value = State.ERROR
                }

                else -> {
                    _state.value = State.LOADING
                }
            }
        }
    }

    fun fetchProductsWithCategory(category: Category) {
        viewModelScope.launch {
            _filteredProduct =
                repository.fetchFilteredProducts(category) as MutableLiveData<List<DomainProduct>>
        }
    }

    fun fetchProductWithId(key: Long) {
        viewModelScope.launch {
            _selectedItem.value = repository.fetchProductWithId(key)
        }
    }

    fun addItemToCart(cart: DomainCart) {
        viewModelScope.launch {
            repository.addToCart(cart.toDbModel())
        }
    }

    fun resetState() {
        _state.postValue(null)
    }

}

enum class State {
    SUCCESS, ERROR, LOADING
}