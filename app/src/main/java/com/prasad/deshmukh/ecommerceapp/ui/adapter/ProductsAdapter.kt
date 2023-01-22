package com.prasad.deshmukh.ecommerceapp.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prasad.deshmukh.ecommerceapp.R
import com.prasad.deshmukh.ecommerceapp.data.Products
import com.prasad.deshmukh.ecommerceapp.utils.VIEW_TYPE_BANNER
import com.prasad.deshmukh.ecommerceapp.utils.VIEW_TYPE_PRODUCT
import com.prasad.deshmukh.ecommerceapp.utils.showToast
import kotlinx.android.synthetic.main.layout_item_banner.view.*
import kotlinx.android.synthetic.main.layout_item_product.view.*



class ProductsAdapter(private val items: List<Any>, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImage: AppCompatImageView = itemView.acivProductImage
        var productItem: ConstraintLayout = itemView.clProductHolder
        var productTitle: AppCompatTextView = itemView.actvProductTitle
        var productDescription: AppCompatTextView = itemView.actvDescription
        var productPrice: AppCompatTextView = itemView.actvPrice
        var offerPrice: AppCompatTextView = itemView.actvOfferPrice
        var brandName: AppCompatTextView = itemView.actvBrandName
    }

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var infoText: AppCompatTextView = itemView.actvInfo
        var layout: ConstraintLayout = itemView.clBannerLayout
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        if (items[position] is Products) {
            return VIEW_TYPE_PRODUCT
        } else if (items[position] is String) {
            return VIEW_TYPE_BANNER
        }
        return -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            VIEW_TYPE_PRODUCT -> {
                val v1: View = inflater.inflate(R.layout.layout_item_product, parent, false)
                viewHolder = ProductViewHolder(v1)
            }
            VIEW_TYPE_BANNER -> {
                val v2: View = inflater.inflate(R.layout.layout_item_banner, parent, false)
                viewHolder = BannerViewHolder(v2)
            }
            else -> {
                val v: View = inflater.inflate(
                    R.layout.layout_item_empty,
                    parent, false
                )
                viewHolder = EmptyViewHolder(v)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder.itemViewType) {
            VIEW_TYPE_PRODUCT -> {
                val productViewHolder = viewHolder as ProductViewHolder
                configureProductsViewHolder(productViewHolder, position)
            }
            VIEW_TYPE_BANNER -> {
                val bannerViewHolder = viewHolder as BannerViewHolder
                configureBannerViewHolder(bannerViewHolder, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun configureProductsViewHolder(productViewHolder: ProductViewHolder, position: Int) {
        val products: Products = items[position] as Products
        Glide.with(context).load(products.productUrl).into(productViewHolder.productImage)
        productViewHolder.productDescription.text = products.productDesc
        productViewHolder.productTitle.text = products.name
        productViewHolder.brandName.text = products.brand
        productViewHolder.productPrice.paint.flags =
            Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
        productViewHolder.productPrice.text = context.getString(R.string.rupee, products.price)
        productViewHolder.offerPrice.text = context.getString(R.string.rupee, products.offerPrice)
        productViewHolder.productItem.setOnClickListener {
            listener.invoke(position)
        }
    }

    private fun configureBannerViewHolder(bannerViewHolder: BannerViewHolder, position: Int) {
        bannerViewHolder.infoText.text = items[position] as String
        bannerViewHolder.layout.setOnClickListener {
            showToast(context,context.getString(R.string.thank_you_text))

        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
}