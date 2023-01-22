package com.prasad.deshmukh.ecommerceapp.repository

import com.prasad.deshmukh.ecommerceapp.data.BaseResponse
import com.prasad.deshmukh.ecommerceapp.data.ProfileData
import com.prasad.deshmukh.ecommerceapp.network.ECommApi
import com.prasad.deshmukh.ecommerceapp.utils.State
import javax.inject.Inject

class ECommRepository @Inject constructor(private val eCommApi: ECommApi) {

    suspend fun getProductsData(): State<BaseResponse> {
        val productsResponse = eCommApi.getProductsData()
        return if (productsResponse.isSuccessful && productsResponse.body() != null) {
            State.Success(productsResponse.body())
        } else {
            State.Error("Something went wrong!!")
        }
    }

    suspend fun getProfileData(): State<ProfileData> {
        val profileResponse = eCommApi.getProfileData()
        return if (profileResponse.isSuccessful && profileResponse.body() != null) {
            State.Success(profileResponse.body())
        } else {
            State.Error("Something went wrong!!")
        }
    }
}