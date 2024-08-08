package com.example.onlinestore.di.binds

import com.example.catalog.repository.GetDataRepository
import com.example.data.goods.GetDataUseCase
import com.example.data.image.contract.RequestContract
import com.example.data.image.usecase.RequestDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogModuleBinds {

    @Binds
    abstract fun bindsGetData(getDataUseCase: GetDataUseCase): GetDataRepository

    @Binds
    abstract fun bindsRequestDatabase(requestDatabase: RequestDatabase): RequestContract



}