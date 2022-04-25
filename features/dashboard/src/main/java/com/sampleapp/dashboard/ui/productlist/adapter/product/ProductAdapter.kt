package com.sampleapp.dashboard.ui.productlist.adapter.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sampleapp.core.utils.loadImage
import com.sampleapp.dashboard.R
import com.sampleapp.dashboard.databinding.ItemProductsBinding
import com.sampleapp.dashboard.domain.models.Product

class ProductAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(
    DiffCallbackProduct()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_products,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ProductViewHolder(
        private val binding: ItemProductsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(model: Product) {
            binding.apply {
                tvProduct.text = model.name
                tvPrice.text = "${model.price} ${model.currency}"
                cvProduct.setOnClickListener {
                    onClick(adapterPosition)
                }

                ivProduct.loadImage(model.url)
            }
        }
    }
}