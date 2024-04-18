package com.example.onlinestore.di

import com.example.catalog.contract.GetDataContract
import com.example.catalog.repository.GetDataRepository
import com.example.catalog.usecase.ItemUseCase
import com.example.data.goods.usecase.GetData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CatalogModule {

    @Provides
    fun providesItemUseCase(
        getDataRepository: GetDataRepository
    ): GetDataContract {
        return ItemUseCase(getDataRepository)
    }

    @Provides
    fun provideGetData(): GetDataRepository {
        return GetData()
    }
}