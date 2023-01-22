package com.prasad.deshmukh.ecommerceapp.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prasad.deshmukh.ecommerceapp.data.BaseResponse
import com.prasad.deshmukh.ecommerceapp.data.ProfileData
import com.prasad.deshmukh.ecommerceapp.repository.ECommRepository
import com.prasad.deshmukh.ecommerceapp.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ECommViewModel @Inject constructor(private val eCommRepository: ECommRepository) :
    ViewModel() {

    private val productsResponseLiveData = MutableLiveData<State<BaseResponse>>()
    val productsResponse: LiveData<State<BaseResponse>>
        get() = productsResponseLiveData

    private val profileResponseLiveData = MutableLiveData<State<ProfileData>>()
    val profileResponse: LiveData<State<ProfileData>>
        get() = profileResponseLiveData

    fun getProductsData() {
        viewModelScope.launch(Dispatchers.IO) {
            productsResponseLiveData.postValue(eCommRepository.getProductsData())
        }
    }

    fun getProfileData() {
        viewModelScope.launch(Dispatchers.IO) {
            profileResponseLiveData.postValue(eCommRepository.getProfileData())
        }
    }
}