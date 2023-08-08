package com.oluwafemi.basehq.utils

sealed class NetworkState<out R> {
    data class Success<out T>(val data: T) : NetworkState<T>()
    data class Error(val exception: String) : NetworkState<Nothing>()
    object Loading : NetworkState<Nothing>()
}