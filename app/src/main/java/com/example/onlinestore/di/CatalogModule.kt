package com.example.onlinestore.di

import android.content.Context
import com.example.catalog.contract.GetDataContract
import com.example.catalog.contract.GetImageContract
import com.example.catalog.repository.GetDataRepository
import com.example.catalog.repository.GetDataTransformerRepository
import com.example.catalog.usecase.ImageUseCase
import com.example.catalog.usecase.ItemUseCase
import com.example.data.goods.usecase.GetDataUseCase
import com.example.data.image.contract.RequestContract
import com.example.data.image.usecase.DataTransformer
import com.example.data.image.usecase.RequestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object CatalogModule {



    @Provides
    fun provideGetData(@ApplicationContext context: Context): GetDataRepository{
        return GetDataUseCase(context)
    }


    @Provides
    fun provideItemUseCase(
        getDataRepository: GetDataRepository
    ): GetDataContract {
        return ItemUseCase(getDataRepository)
    }

    @Provides
    fun provideRequestDatabase(): RequestContract {
        return RequestDatabase()
    }

    @Provides
    fun provideGetImage(
        requestContract: RequestContract,
    ): GetDataTransformerRepository {
        return DataTransformer(requestContract)
    }

    @Provides
    fun provideImageUseCase(
        getDataTransformerRepository: GetDataTransformerRepository
    ): GetImageContract {
        return ImageUseCase(getDataTransformerRepository)
    }

}
