package com.sampleapp.local.data.db


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sampleapp.local.data.getCategories
import com.sampleapp.local.data.getProducts
import com.sampleapp.local.db.FoodStoreDataBase
import com.sampleapp.local.db.daos.CategoryDao
import com.sampleapp.local.db.daos.ProductDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var categoryDao: CategoryDao
    private lateinit var productDao: ProductDao
    private lateinit var dataBase: FoodStoreDataBase

    @Before
    fun setup() {
        dataBase = DBFactory.getInMemoryDb()
        productDao = dataBase.productDao()
        categoryDao = dataBase.categoryDao()
    }

    /**
     * In this test we are inserting items in products and category entities and then verifying that record.
     */

    @Test
    fun insertCategoriesAndProducts() = runBlockingTest {
        categoryDao.insert(getCategories())
        productDao.insert(getProducts())

        val response = categoryDao.getAllCategoriesWithProducts().first()[0]

        assertThat(response.category.categoryId).isEqualTo("35602")
        assertThat(response.products[0].productId).isEqualTo("1")
        assertThat(response.products[0].name).isEqualTo("bread")
        assertThat(response.products[0].categoryId).isEqualTo("35602")
    }


    /**
     * In this test we are deleting all items from db.
     */
    @Test
    fun insertCategoriesAndDeleteAll() = runBlockingTest {
        categoryDao.insert(getCategories())
        productDao.insert(getProducts())
        val response = categoryDao.getAllCategoriesWithProducts().first()
        assertThat(response.isNotEmpty()).isTrue()
        categoryDao.deleteAll()
        productDao.deleteAll()
        val afterResponse = categoryDao.getAllCategoriesWithProducts().first()
        assertThat(afterResponse.isEmpty()).isTrue()
    }

}