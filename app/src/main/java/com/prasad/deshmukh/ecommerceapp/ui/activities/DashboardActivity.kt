package com.prasad.deshmukh.ecommerceapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.prasad.deshmukh.ecommerceapp.R
import com.prasad.deshmukh.ecommerceapp.ui.adapter.ViewPagerAdapter
import com.prasad.deshmukh.ecommerceapp.ui.fragments.ProductsFragment
import com.prasad.deshmukh.ecommerceapp.ui.fragments.ProfileFragment
import com.prasad.deshmukh.ecommerceapp.viewmodels.ECommViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    @Inject
    lateinit var eCommViewModel: ECommViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setUpToolbar(getString(R.string.dashboard))
        setUpViewPager()
    }

    private fun setUpToolbar(title: String) {
        toolbar_title.text = title
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setUpViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ProductsFragment.newInstance(), "Products")
        adapter.addFragment(ProfileFragment.newInstance(), "Profile")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text?.equals(getString(R.string.profile)) == true) {
                    setUpToolbar(getString(R.string.profile))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}