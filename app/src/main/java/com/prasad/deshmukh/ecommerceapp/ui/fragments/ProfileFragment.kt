package com.prasad.deshmukh.ecommerceapp.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prasad.deshmukh.ecommerceapp.R
import com.prasad.deshmukh.ecommerceapp.data.ProfileData
import com.prasad.deshmukh.ecommerceapp.ui.activities.LoginActivity
import com.prasad.deshmukh.ecommerceapp.utils.SHARED_PREF
import com.prasad.deshmukh.ecommerceapp.utils.State
import com.prasad.deshmukh.ecommerceapp.utils.isInternetConnected
import com.prasad.deshmukh.ecommerceapp.utils.showToast
import com.prasad.deshmukh.ecommerceapp.viewmodels.ECommViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_profile_info.view.*
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    @Inject
    lateinit var eCommViewModel: ECommViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getProfileData()
    }

    private fun getProfileData() {
        eCommViewModel.profileResponse.observe(this) { responseState ->
            when (responseState) {
                is State.Loading -> {}
                is State.Success -> {
                    setUpUI(responseState.data)
                }
                is State.Error -> {
                    progressBar.visibility = View.GONE
                }
            }
        }

        if (context?.let { isInternetConnected(it) } == true) {
            eCommViewModel.getProfileData()
        } else {
            context?.let { showToast(it,getString(R.string.internet_not_connected)) }
        }
    }

    private fun setUpUI(profileData: ProfileData?) {
        profileData?.let {
            clProfileData.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            actvUserName.text = getString(R.string.firstname_lastname, it.firstname, it.lastName)
            layoutDob.label.text = getString(R.string.dob)
            layoutDob.value.text = it.dob
            layoutAddress.label.text = getString(R.string.address)
            layoutAddress.value.text = it.address
            layoutPoints.label.text = getString(R.string.points_earned)
            layoutPoints.value.text = it.pointsEarned
            layoutWallet.label.text = getString(R.string.wallet_balance)
            layoutWallet.value.text = it.walletBalance
            acbLogout.setOnClickListener {
                context?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)?.edit()?.clear()
                    ?.apply()
                startActivity(Intent(activity, LoginActivity::class.java))
                activity?.finish()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProfileFragment()
    }
}