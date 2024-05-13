package com.example.onlinestore.di

import android.content.Context
import com.example.catalog.contract.GetDataContract
import com.example.catalog.contract.GetImageContract
import com.example.catalog.repository.GetDataRepository
import com.example.catalog.repository.GetImageRepository
import com.example.catalog.usecase.ImageUseCase
import com.example.catalog.usecase.ItemUseCase
import com.example.data.goods.usecase.GetData
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
        return GetData(context)
    }


    @Provides
    fun provideItemUseCase(
        getDataRepository: GetDataRepository
    ): GetDataContract {
        return ItemUseCase(getDataRepository)
    }

   /* @Provides
    fun provideGetImage(): GetImageRepository {
        return GetImage()
    }*/

    @Provides
    fun provideImageUseCase(
        getImageRepository: GetImageRepository
    ): GetImageContract {
        return ImageUseCase(getImageRepository)
    }

}
