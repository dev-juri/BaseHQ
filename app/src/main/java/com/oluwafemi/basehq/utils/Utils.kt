package com.oluwafemi.basehq.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

enum class Category(val value: String) {
    Electronics("electronics"),
    Jewelery("jewelery"),
    MenClothing("men's clothing"),
    WomenClothing("women's clothing");

    companion object {
        fun fromValue(value: String): Category = when (value) {
            "electronics" -> Electronics
            "jewelery" -> Jewelery
            "men's clothing" -> MenClothing
            "women's clothing" -> WomenClothing
            else -> WomenClothing
        }

        fun toValue(category: Category): String = when (category) {
            Electronics -> "Electronics"
            Jewelery -> "Jewelery"
            MenClothing -> "Men's clothing"
            WomenClothing -> "Women's clothing"
        }
    }
}

// Check internet connection
fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetwork
        if (activeNetworkInfo != null) {
            return true
        }

    }
    return false
}