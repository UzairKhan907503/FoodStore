package com.sampleapp.dashboard.ui.productlist.adapter.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sampleapp.dashboard.R
import com.sampleapp.dashboard.databinding.ItemCategoryProductsBinding
import com.sampleapp.dashboard.domain.models.Category
import com.sampleapp.dashboard.ui.productlist.adapter.product.ProductAdapter

class CategoryAdapter(
    private val onClick: (Int, Int) -> Unit
) : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(
    DiffCallbackCategory()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_category_products,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class CategoryViewHolder(
        private val binding: ItemCategoryProductsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var adapter: ProductAdapter

        fun bind(model: Category) {
            binding.apply {
                tvCategoryTitle.text = model.name
                adapter = ProductAdapter { productPosition ->
                    onClick(adapterPosition, productPosition)
                }
                rvProduct.adapter = adapter
                adapter.submitList(model.products)
            }
        }
    }
}