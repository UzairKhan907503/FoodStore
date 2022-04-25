package com.sampleapp.dashboard.ui.productlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sampleapp.core.ui.base.BaseFragmentWithVM
import com.sampleapp.dashboard.ui.productlist.adapter.category.CategoryAdapter
import com.sampleapp.core.utils.collectWhenStarted
import com.sampleapp.core.utils.invisible
import com.sampleapp.core.utils.navigate
import com.sampleapp.core.utils.visible
import com.sampleapp.dashboard.R
import com.sampleapp.dashboard.databinding.FragmentFoodItemsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FoodItemsListFragment :
    BaseFragmentWithVM<FragmentFoodItemsListBinding, FoodItemsListViewModel>() {
    private val mViewModel by viewModels<FoodItemsListViewModel>()
    private lateinit var adapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        adapter = CategoryAdapter(getViewModel().onItemClicked)
        binding.rvCategories.adapter = adapter
    }

    private fun initObservers() {
        collectWhenStarted {
            getViewModel().events.collect { event ->
                when (event) {
                    is FoodItemsListEvents.ProductSelected -> {
                        navigate(
                            findNavController(),
                            FoodItemsListFragmentDirections.actionFoodItemsListToProductsDetails(
                                event.product
                            )
                        )
                    }
                }
            }
        }

        collectWhenStarted {
            getViewModel().state.collect { states ->
                when (states) {
                    is FoodItemsListStates.ProductsDetailsFetched -> {
                        binding.progress.invisible()
                        adapter.submitList(states.categoryList)
                    }
                    is FoodItemsListStates.Loading -> {
                        binding.progress.visible()
                    }
                }
            }
        }

    }

    override fun getViewModel() = mViewModel

    override fun getLayoutId(): Int = R.layout.fragment_food_items_list
}