package com.prasad.deshmukh.ecommerceapp.data

import com.google.gson.annotations.SerializedName
import com.prasad.deshmukh.ecommerceapp.data.Products


data class Data (
  @SerializedName("products" ) var products : ArrayList<Products> = arrayListOf()
)