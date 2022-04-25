package com.sampleapp.dashboard.ui.productlist.adapter.product

import androidx.recyclerview.widget.DiffUtil
import com.sampleapp.dashboard.domain.models.Product


class DiffCallbackProduct : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(
        oldItem: Product,
        newItem: Product
    ) = (oldItem.productId == newItem.productId) and (oldItem.categoryId == newItem.categoryId)

    override fun areContentsTheSame(
        oldItem: Product,
        newItem: Product
    ) = oldItem == newItem
}