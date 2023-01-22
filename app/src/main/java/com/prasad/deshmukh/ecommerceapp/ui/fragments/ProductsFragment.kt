package com.prasad.deshmukh.ecommerceapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.prasad.deshmukh.ecommerceapp.R
import com.prasad.deshmukh.ecommerceapp.data.Products
import com.prasad.deshmukh.ecommerceapp.ui.activities.ProductInfoActivity
import com.prasad.deshmukh.ecommerceapp.ui.adapter.ProductsAdapter
import com.prasad.deshmukh.ecommerceapp.utils.SELECTED_PRODUCT
import com.prasad.deshmukh.ecommerceapp.utils.State
import com.prasad.deshmukh.ecommerceapp.utils.isInternetConnected
import com.prasad.deshmukh.ecommerceapp.utils.showToast
import com.prasad.deshmukh.ecommerceapp.viewmodels.ECommViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_products.*
import javax.inject.Inject


@AndroidEntryPoint
class ProductsFragment : Fragment() {

    @Inject
    lateinit var eCommViewModel: ECommViewModel

    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAndObserveProductsData()
    }

    override fun onResume() {
        super.onResume()
        shimmerLayout.startShimmerAnimation()
    }

    private fun getAndObserveProductsData() {
        eCommViewModel.productsResponse.observe(this) { responseState ->
            when (responseState) {
                is State.Success -> {
                    val heterogeneousList = ArrayList<Any>()
                    responseState.data?.data?.products?.let { heterogeneousList.addAll(it) }
                    heterogeneousList.add(1, getString(R.string.banner_title))
                    productsAdapter = ProductsAdapter(heterogeneousList) {
                        startActivity(Intent(activity, ProductInfoActivity::class.java).apply {
                            putExtra(SELECTED_PRODUCT, heterogeneousList.getOrNull(it) as Products)
                        })
                    }
                    shimmerLayout.stopShimmerAnimation()
                    shimmerLayout.visibility = View.GONE
                    rvProducts.visibility = View.VISIBLE
                    rvProducts.adapter = productsAdapter
                    rvProducts.layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    rvProducts.setHasFixedSize(true)
                }
                is State.Loading -> {

                }
                is State.Error -> {
                    activity?.let { showToast(it, responseState.errorMessage ?: "") }
                }
            }
        }

        if (context?.let { isInternetConnected(it) } == true) {
            eCommViewModel.getProductsData()
        } else {
            context?.let { showToast(it, getString(R.string.internet_not_connected)) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProductsFragment()
    }
}