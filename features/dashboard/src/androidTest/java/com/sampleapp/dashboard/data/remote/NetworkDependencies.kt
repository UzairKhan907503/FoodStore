package com.sampleapp.dashboard.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sampleapp.dashboard.data.remote.api.ApiService
import com.sampleapp.remote.utils.ApiUtils
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit

@ExperimentalSerializationApi
object NetworkDependencies {


    fun getMockApiClient(mockServer: MockWebServer): ApiService {
        return getApiClient(
            getRetrofit(
                getOkHttpClient(), mockServer
            )
        )
    }


    private fun getOkHttpClient() =
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()


    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private fun getRetrofit(okHttpClient: OkHttpClient, mockServer: MockWebServer): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(mockServer.url(ApiUtils.BASE_URL))
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    private fun getApiClient(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}
