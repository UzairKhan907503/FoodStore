package com.sampleapp.dashboard.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sampleapp.dashboard.domain.usecases.CategoryUseCase
import com.sampleapp.dashboard.ui.productlist.FoodItemsListViewModel
import com.google.common.truth.Truth.assertThat
import com.sampleapp.core.ui.base.BaseViewModel
import com.sampleapp.dashboard.data.ERROR_MESSAGE
import com.sampleapp.dashboard.data.Response
import com.sampleapp.dashboard.data.getCategories
import com.sampleapp.dashboard.data.getCategoriesResourceFlow
import com.sampleapp.dashboard.ui.productlist.FoodItemsListStates
import com.sampleapp.dashboard.utils.MainCoroutinesRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FoodItemsListViewModelTest {
    private val categoryUseCase: CategoryUseCase = mockk()
    private lateinit var viewModel: FoodItemsListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        viewModel = FoodItemsListViewModel(categoryUseCase)
    }

    @Test
    fun `get categories with products when server response valid`() {
        coEvery { categoryUseCase.getAllWithProducts() } returns getCategoriesResourceFlow(Response.Valid)
        runBlocking {
            viewModel.state.take(2).toList().run {
                assertThat(get(0)).isEqualTo(FoodItemsListStates.Loading)
                assertThat(get(1)).isEqualTo(
                    FoodItemsListStates.ProductsDetailsFetched(
                        getCategories()
                    )
                )
            }
        }
    }

    @Test
    fun `get categories with products when server response Invalid and db have data`() {
        coEvery {
            categoryUseCase.getAllWithProducts()
        } returns getCategoriesResourceFlow(Response.Invalid) andThen
                getCategoriesResourceFlow(Response.Valid)
        runBlocking {
            viewModel.baseEvents.first().let {
                assertThat(it).isEqualTo(BaseViewModel.BaseEvent.EventError(ERROR_MESSAGE))
            }

            viewModel.state.first().let {
                assertThat(it).isEqualTo(
                    FoodItemsListStates.ProductsDetailsFetched(
                        getCategories()
                    )
                )
            }
        }
    }
}