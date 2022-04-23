package com.sampleapp.dashboard.ui.productlist.adapter.product

import androidx.recyclerview.widget.DiffUtil
import com.sampleapp.dashboard.domain.models.Product


class DiffCallbackProduct : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(
        oldItem: Product,
        newItem: Product
    ) = oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(
        oldItem: Product,
        newItem: Product
    ) = oldItem.hashCode() == newItem.hashCode()
}