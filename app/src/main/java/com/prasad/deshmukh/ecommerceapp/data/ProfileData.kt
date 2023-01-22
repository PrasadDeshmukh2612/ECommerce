package com.prasad.deshmukh.ecommerceapp.data

import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("id") var id: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("firstname") var firstname: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("dob") var dob: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("pointsEarned") var pointsEarned: String? = null,
    @SerializedName("walletBalance") var walletBalance: String? = null
)
