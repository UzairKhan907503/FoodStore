package com.sampleapp.dashboard.ui.productdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.sampleapp.core.ui.base.BaseFragmentWithVM
import com.sampleapp.core.utils.getCompleteUrl
import com.sampleapp.core.utils.getProgressDrawable
import com.sampleapp.core.utils.loadImage
import com.sampleapp.dashboard.R
import com.sampleapp.dashboard.databinding.FragmentProductDetailsBinding
import com.sampleapp.dashboard.domain.models.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsDetailsFragment :
    BaseFragmentWithVM<FragmentProductDetailsBinding, ProductDetailsViewModel>() {
    private val mViewModel by viewModels<ProductDetailsViewModel>()
    private val args by navArgs<ProductsDetailsFragmentArgs>()
    override fun getLayoutId(): Int = R.layout.fragment_product_details

    override fun getViewModel(): ProductDetailsViewModel = mViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(args.product)
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(model: Product) {
        binding.apply {
            tvProduct.text = model.name
            tvPrice.text = "${model.price} ${model.currency}"
            ivProduct.loadImage(model.url)
        }
    }


}