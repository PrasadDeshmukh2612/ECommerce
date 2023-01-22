package com.prasad.deshmukh.ecommerceapp.ui.activities

import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.prasad.deshmukh.ecommerceapp.R
import com.prasad.deshmukh.ecommerceapp.data.Products
import com.prasad.deshmukh.ecommerceapp.utils.SELECTED_PRODUCT
import kotlinx.android.synthetic.main.activity_product_info.*

class ProductInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_info)
        intent.extras?.get(SELECTED_PRODUCT)?.let {
            val selectedItem = it as Products
            Glide.with(this).load(selectedItem.productUrl).into(acivSelectedProductImage)
            actvSelectedProductDesc.text =
                getString(R.string.description_details, selectedItem.productDesc)
            actvSelectedProductName.text = selectedItem.name
            selectedItem.brand?.let { it1 -> setUpToolbar(it1) }
            actvPrice.paint.flags =
                Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
            actvPrice.text = getString(R.string.rupee, selectedItem.price)
            actvOfferPrice.text = getString(R.string.rupee, selectedItem.offerPrice)
        }
    }

    private fun setUpToolbar(title: String) {
        toolbar_title.text = title
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}