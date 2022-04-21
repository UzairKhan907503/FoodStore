package com.sampleapp.dashboard.domain.usecases

import com.sampleapp.dashboard.domain.repository.CategoryRepository

import javax.inject.Inject

class CategoryUseCase  @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend fun getAllWithProducts() = categoryRepository.getAllCategories()
    
}