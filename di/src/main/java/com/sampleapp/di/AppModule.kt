package com.sampleapp.di

import com.sampleapp.navigation.MainActivityNavigation
import com.sampleapp.navigation.MainActivityNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNavigator() : MainActivityNavigation = MainActivityNavigationImpl()
}