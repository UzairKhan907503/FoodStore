package com.sampleapp.dashboard.datasource

import com.google.common.truth.Truth.assertThat
import com.sampleapp.dashboard.data.getCategories
import com.sampleapp.dashboard.data.getCategoriesDBFlow
import com.sampleapp.dashboard.data.local.datasources.CategoryLocalDataSourceImpl
import com.sampleapp.dashboard.domain.datasources.local.CategoryLocalDataSource
import com.sampleapp.dashboard.utils.toDBModel
import com.sampleapp.local.db.daos.CategoryDao
import com.sampleapp.local.db.models.CategoryDBModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoryLocalDataSourceTest {

    private val categoryDao = mockk<CategoryDao>()
    private lateinit var categoryDataSource: CategoryLocalDataSource

    @Before
    fun setup() {
        categoryDataSource = CategoryLocalDataSourceImpl(categoryDao)
    }

    @Test
    fun `get categories with products returns data from db and map it to domain model`() {
        every { categoryDao.getAllCategoriesWithProducts() } returns getCategoriesDBFlow()
        runBlockingTest {
            categoryDataSource.getCategoriesWithProducts().first().let {
                assertThat(it).isEqualTo(getCategories())
            }
        }
    }

    @Test
    fun `save categories by converting domain model category to db model`() {
        val inputData = slot<List<CategoryDBModel>>()

        coEvery { categoryDao.insert(capture(inputData)) } returns Unit
        runBlockingTest {
            categoryDataSource.saveCategoriesWithProducts(getCategories())
            val categories = inputData.captured
            assertThat(categories).isEqualTo(getCategories().toDBModel())
        }
    }

    @Test
    fun `delete all categories`() {
        coEvery { categoryDao.deleteAll() } returns Unit
        runBlockingTest { categoryDataSource.deleteAll() }
        coVerify { categoryDao.deleteAll() }
        confirmVerified(categoryDao)
    }
}