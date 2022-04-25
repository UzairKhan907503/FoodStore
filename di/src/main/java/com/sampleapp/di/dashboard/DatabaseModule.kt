package com.sampleapp.di.dashboard

import android.content.Context
import com.sampleapp.local.db.FoodStoreDataBase
import com.sampleapp.local.db.daos.CategoryDao
import com.sampleapp.local.db.daos.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCategoryDao(foodStoreDataBase: FoodStoreDataBase) : CategoryDao {
        return foodStoreDataBase.categoryDao()
    }

    @Singleton
    @Provides
    fun provideProductDao(foodStoreDataBase: FoodStoreDataBase) : ProductDao {
        return foodStoreDataBase.productDao()
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext appContext: Context): FoodStoreDataBase {
        return FoodStoreDataBase.getDatabase(appContext)
    }
}