package com.sampleapp.dashboard.ui.productlist.adapter.category

import androidx.recyclerview.widget.DiffUtil
import com.sampleapp.dashboard.domain.models.Category


class DiffCallbackCategory : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(
        oldItem: Category,
        newItem: Category
    ) = oldItem.categoryId == newItem.categoryId

    override fun areContentsTheSame(
        oldItem: Category,
        newItem: Category
    ) = oldItem == newItem
}