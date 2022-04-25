package com.sampleapp.dashboard.repository

import com.google.common.truth.Truth.assertThat
import com.sampleapp.core.repoutils.manageDataSource
import com.sampleapp.dashboard.data.*
import com.sampleapp.dashboard.domain.datasources.local.CategoryLocalDataSource
import com.sampleapp.dashboard.domain.datasources.local.ProductLocalDataSource
import com.sampleapp.dashboard.domain.datasources.remote.CategoryWithProductsRemoteDataSource
import com.sampleapp.dashboard.domain.models.Category
import com.sampleapp.dashboard.domain.repository.CategoryRepository
import com.sampleapp.dashboard.domain.repository.CategoryRepositoryImpl
import com.sampleapp.remote.utils.Resource
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class CategoryRepositoryTest {
    private lateinit var repository: CategoryRepository
    private val remoteDataSource: CategoryWithProductsRemoteDataSource = mockk()

    private val categoryLocalDataSource: CategoryLocalDataSource = mockk(relaxed = true)

    private val productLocalDataSource: ProductLocalDataSource = mockk(relaxed = true)

    @Before
    fun setup() {
        repository = CategoryRepositoryImpl(
            remoteDataSource = remoteDataSource,
            categoryPDS = categoryLocalDataSource,
            productPDS = productLocalDataSource
        )
    }

    @ExperimentalTime
    @Test
    fun `get all categories for Valid response`() {
        every { remoteDataSource.getCategoriesWithProducts() } returns getCategoriesResourceFlow(
            Response.Valid
        )
        val slot = slot<List<Category>>()
        coEvery { categoryLocalDataSource.saveCategoriesWithProducts(capture(slot)) } returns Unit
        coEvery { categoryLocalDataSource.getCategoriesWithProducts() } returns flow { emit(slot.captured) }

        runBlocking {
            repository.getAllCategories().collect {
                assertThat(it).isEqualTo(Resource.Valid(slot.captured))
            }
        }
    }

    @ExperimentalTime
    @Test
    fun `get all categories for Invalid response from server and persistence is empty`() {
        every { remoteDataSource.getCategoriesWithProducts() } returns getCategoriesResourceFlow(
            Response.Invalid
        )
        coEvery { categoryLocalDataSource.getCategoriesWithProducts() } returns flow { emit(listOf()) }
        runBlocking {
            repository.getAllCategories().take(2).toList().run {
                assertThat(get(0)).isEqualTo(Resource.Invalid<List<Category>>(ERROR_MESSAGE))
                assertThat(get(1)).isEqualTo(Resource.Valid<List<Category>>(listOf()))
            }
        }
    }

    @ExperimentalTime
    @Test
    fun `get all categories if remote response is Invalid but data is stored in persistence`() {
        every { remoteDataSource.getCategoriesWithProducts() } returns getCategoriesResourceFlow(
            Response.Invalid
        )
        coEvery { categoryLocalDataSource.getCategoriesWithProducts() } returns getFlowCategories()
        runBlocking {
            repository.getAllCategories().take(2).toList().run {
                assertThat(get(0)).isEqualTo(Resource.Invalid<List<Category>>(ERROR_MESSAGE))
                assertThat(get(1)).isEqualTo(Resource.Valid(getCategories()))
            }
        }
    }

    @ExperimentalTime
    @Test
    fun `manage data source test when remote response is valid`() {
        val list = mutableListOf<Category>()
        runBlocking {
            manageDataSource(
                getDataFromServer = { getCategoriesResourceFlow(Response.Valid) },
                getDataFromPersistence = { flow { emit(list) } },
                updateLocal = { list.addAll(it) }
            ).first().let {
                assertThat(it).isEqualTo(Resource.Valid(list))
            }
        }
    }

    @ExperimentalTime
    @Test
    fun `manage data source test when remote response is Invalid and cache is not empty`() {
        var list = getCategories()
        runBlocking {
            manageDataSource(
                getDataFromServer = { getCategoriesResourceFlow(Response.Invalid) },
                getDataFromPersistence = { flow { emit(list) } },
                updateLocal = { list = it }
            ).take(2).toList().run {
                assertThat(get(0)).isEqualTo(Resource.Invalid<List<Category>>(ERROR_MESSAGE))
                assertThat(get(1)).isEqualTo(Resource.Valid(list))
            }
        }
    }

}