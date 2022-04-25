package com.sampleapp.dashboard.ui.productlist

import androidx.lifecycle.viewModelScope
import com.sampleapp.core.ui.base.BaseViewModel
import com.sampleapp.dashboard.domain.models.Category
import com.sampleapp.dashboard.domain.usecases.CategoryUseCase
import com.sampleapp.remote.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodItemsListViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase
) : BaseViewModel() {
    private var intents = Channel<FoodItemsListEvents>()
    val events get() = intents.receiveAsFlow()
    private val weatherList = MutableStateFlow(listOf<Category>())
    private val weatherState = MutableStateFlow<FoodItemsListStates>(FoodItemsListStates.Loading)
    val state = weatherState.asStateFlow()


    val onItemClicked: (Int, Int) -> Unit = { category, product ->
        viewModelScope.launch {
            intents.send(FoodItemsListEvents.ProductSelected(weatherList.value[category].products[product]))
        }
    }

    init {
        getCategoriesWithProducts()
    }

    private fun getCategoriesWithProducts() {
        viewModelScope.launch {
            categoryUseCase.getAllWithProducts().collect { response ->
                when(response){
                    is Resource.Valid -> {
                        response.data.let {
                            weatherList.value = it
                            weatherState.value = FoodItemsListStates.ProductsDetailsFetched(
                                it
                            )
                        }
                    }
                    is Resource.Invalid -> sendError(response.message)
                    is Resource.Loading -> weatherState.value = FoodItemsListStates.Loading
                }
            }
        }
    }


}