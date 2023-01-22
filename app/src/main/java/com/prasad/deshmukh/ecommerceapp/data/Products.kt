package com.prasad.deshmukh.ecommerceapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Products(
    @SerializedName("id") var id: String? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("productDesc") var productDesc: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("offerPrice") var offerPrice: String? = null,
    @SerializedName("productUrl") var productUrl: String? = null
):Parcelable