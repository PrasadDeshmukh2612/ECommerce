package com.prasad.deshmukh.ecommerceapp.utils

sealed class State<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : State<T>()
    class Success<T>(data: T? = null) : State<T>(data = data)
    class Error<T>(errorMessage: String?) : State<T>(errorMessage = errorMessage)
}
