package com.example.onlinestore.di

import com.example.catalog.contract.GetDataContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CatalogModule {

    @Provides
    fun provideCatalogViewmodel(
        getDataContract: GetDataContract
    ){

    }
}