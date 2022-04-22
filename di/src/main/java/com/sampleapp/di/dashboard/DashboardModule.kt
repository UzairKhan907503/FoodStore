package com.sampleapp.di.dashboard

import com.sampleapp.dashboard.data.local.datasources.CategoryLocalDataSourceImpl
import com.sampleapp.dashboard.data.local.datasources.ProductLocalDataSourceImpl
import com.sampleapp.dashboard.data.remote.datasources.CategoryWithProductsRemoteDataSourceImpl
import com.sampleapp.dashboard.domain.datasources.local.CategoryLocalDataSource
import com.sampleapp.dashboard.domain.datasources.local.ProductLocalDataSource
import com.sampleapp.dashboard.domain.datasources.remote.CategoryWithProductsRemoteDataSource
import com.sampleapp.dashboard.domain.repository.CategoryRepository
import com.sampleapp.dashboard.domain.repository.CategoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DashboardModule {

    @Provides
    @ViewModelScoped
    fun provideRemoteDataSource(
        dataSourceImpl: CategoryWithProductsRemoteDataSourceImpl
    ): CategoryWithProductsRemoteDataSource {
        return dataSourceImpl
    }

    @Provides
    @ViewModelScoped
    fun provideWeatherRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ) : CategoryRepository = categoryRepositoryImpl

    @Provides
    @ViewModelScoped
    fun provideCategoryPersistenceDataSource(
        categoryLocalDataSource: CategoryLocalDataSourceImpl
    ): CategoryLocalDataSource {
        return categoryLocalDataSource
    }

    @Provides
    @ViewModelScoped
    fun provideProductPersistenceDataSource(
        productLocalDataSourceImpl: ProductLocalDataSourceImpl
    ): ProductLocalDataSource {
        return productLocalDataSourceImpl
    }
}