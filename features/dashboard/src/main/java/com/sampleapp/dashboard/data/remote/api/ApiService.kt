package com.sampleapp.dashboard.data.remote.api

import com.sampleapp.dashboard.data.remote.models.CategoryDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getAllData(): Response<List<CategoryDTO>>
}