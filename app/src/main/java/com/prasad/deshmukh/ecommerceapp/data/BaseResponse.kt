package com.prasad.deshmukh.ecommerceapp.data

import com.google.gson.annotations.SerializedName


data class BaseResponse (
  @SerializedName("data" ) var data : Data? = null
)