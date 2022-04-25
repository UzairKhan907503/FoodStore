package com.sampleapp.dashboard.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sampleapp.dashboard.data.remote.api.ApiService
import com.sampleapp.dashboard.data.remote.datasources.CategoryWithProductsRemoteDataSourceImpl
import com.sampleapp.dashboard.domain.datasources.remote.CategoryWithProductsRemoteDataSource
import com.sampleapp.remote.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class CategoryRemoteDataSourceTest {

    @get:Rule
    val instantTaskExecutorRule =  InstantTaskExecutorRule()

    lateinit var mockServer : MockWebServer

    private lateinit var categoryRemoteDataSource: CategoryWithProductsRemoteDataSource

    private lateinit var apiService: ApiService


    @Before
    fun setup(){
        mockServer = MockWebServer()
        mockServer.dispatcher = MockDispatcher()
        mockServer.start()
        apiService = NetworkDependencies.getMockApiClient(mockServer)
        categoryRemoteDataSource = CategoryWithProductsRemoteDataSourceImpl(apiService)
    }


    /**
     * make sure you are connected to internet only then
     */
    @Test
    fun getValidData(){
        val data = categoryRemoteDataSource.getCategoriesWithProducts()
        runBlocking {
            data.take(2).toList().run {
                assertThat(get(0)).isEqualTo(Resource.Loading(null))
                assertThat((get(1) as Resource.Valid).data[0].categoryId).isEqualTo("36802")
                assertThat((get(1) as Resource.Valid).data[0].products[0].productId).isEqualTo("1")
            }
        }
    }

    @After
    fun clean(){
        mockServer.shutdown()
    }


}