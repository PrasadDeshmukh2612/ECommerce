package com.prasad.deshmukh.ecommerceapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Toast

const val BASE_URL = "https://run.mocky.io/"
const val SELECTED_PRODUCT = "selected_product"
const val IS_LOGGED_IN = "isLoggedIn"
const val SHARED_PREF = "ecommsharedpreference"
const val VIEW_TYPE_PRODUCT = 1
const val VIEW_TYPE_BANNER = 2


fun isInternetConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm.activeNetwork != null && cm.getNetworkCapabilities(cm.activeNetwork) != null
    } else {
        cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnectedOrConnecting
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}