package com.sampleapp.dashboard.datasource

import com.sampleapp.dashboard.data.getCategories
import com.sampleapp.dashboard.data.local.datasources.ProductLocalDataSourceImpl
import com.sampleapp.dashboard.domain.datasources.local.ProductLocalDataSource
import com.sampleapp.local.db.daos.ProductDao
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductLocalDataSourceTest {

    private val productDao = mockk<ProductDao>()

    private lateinit var productLocalDataSource: ProductLocalDataSource

    @Before
    fun setup() {
        productLocalDataSource = ProductLocalDataSourceImpl(productDao)
    }

    @Test
    fun `save list of products`() = runBlockingTest {
        coEvery { productDao.insert(any()) } returns Unit
        productLocalDataSource.saveProducts(getCategories().flatMapTo(mutableListOf()) { it.products })
        coVerify { productDao.insert(any()) }
        confirmVerified(productDao)
    }

    @Test
    fun `delete list of products`() = runBlockingTest {
        coEvery { productDao.deleteAll() } returns Unit
        productLocalDataSource.deleteAll()
        coVerify { productDao.deleteAll() }
        confirmVerified(productDao)
    }

}