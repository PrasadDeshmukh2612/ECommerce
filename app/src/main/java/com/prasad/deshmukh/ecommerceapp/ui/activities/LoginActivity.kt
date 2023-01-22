package com.prasad.deshmukh.ecommerceapp.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prasad.deshmukh.ecommerceapp.ECommApplication
import com.prasad.deshmukh.ecommerceapp.R
import com.prasad.deshmukh.ecommerceapp.utils.IS_LOGGED_IN
import com.prasad.deshmukh.ecommerceapp.utils.SHARED_PREF
import com.prasad.deshmukh.ecommerceapp.utils.showToast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    var sharedPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPref = getSharedPreferences(
            SHARED_PREF,
            Context.MODE_PRIVATE
        )
        acbLogin.setOnClickListener {
            if (acetUsername.text.isNullOrEmpty()) {
                showToast(this, getString(R.string.username_error_messge))
            } else if (acetPassword.text.isNullOrEmpty()) {
                showToast(this, getString(R.string.password_error_messge))
            } else if (acetUsername.text.toString() != "wjohn" && acetPassword.text.toString() != "123456789") {
                showToast(this, getString(R.string.error))
            } else {
                showToast(this, getString(R.string.successfully_logged_in))
                sharedPref?.edit()?.putBoolean(IS_LOGGED_IN, true)?.apply()
                navigateToDashboard()
            }
        }

        checkIfLoggedIn()
    }

    private fun navigateToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    private fun checkIfLoggedIn() {
        if (sharedPref?.getBoolean(IS_LOGGED_IN, false) == true) {
            navigateToDashboard()
        }
    }

}