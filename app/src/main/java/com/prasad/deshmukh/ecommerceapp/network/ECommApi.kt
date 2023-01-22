package com.prasad.deshmukh.ecommerceapp.network

import com.prasad.deshmukh.ecommerceapp.data.BaseResponse
import com.prasad.deshmukh.ecommerceapp.data.ProfileData
import retrofit2.Response
import retrofit2.http.GET

interface ECommApi {

    @GET("v3/bc09a745-4346-4025-9611-9da76366dbbc")
    suspend fun getProductsData() : Response<BaseResponse>

    @GET("v3/aaf97364-eedc-46a5-8f9e-56eb4b3cedd2")
    suspend fun getProfileData() : Response<ProfileData>
}