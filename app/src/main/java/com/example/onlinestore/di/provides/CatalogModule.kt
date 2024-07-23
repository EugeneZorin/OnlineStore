package com.example.onlinestore.di.provides

import com.example.catalog.contract.GetDataContract
import com.example.catalog.contract.GetImageContract
import com.example.catalog.repository.GetDataRepository
import com.example.catalog.repository.GetDataTransformerRepository
import com.example.catalog.usecase.ImageUseCase
import com.example.catalog.usecase.ItemUseCase
import com.example.data.accounts.RequestValidationPasswordFactory
import com.example.data.image.contract.RequestContract
import com.example.data.image.usecase.DataTransformer
import com.example.registration.contract.RequestValidationPasswordContract
import com.example.registration.contract.ValidationNumberPhoneContract
import com.example.registration.repository.RequestValidationPasswordRepository
import com.example.registration.repository.ValidationNumberPhoneRepository
import com.example.registration.usecase.validation.request.ValidationNumberPhone
import com.example.registration.usecase.validation.request.ValidationPasswordImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object CatalogModule {

    @Provides
    fun provideItemUseCase(
        getDataRepository: GetDataRepository
    ): GetDataContract {
        return ItemUseCase(getDataRepository)
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

    @Provides
    fun provideValidationPhoneNumber(
        validationNumberPhoneRepository: ValidationNumberPhoneRepository
    ): ValidationNumberPhoneContract {
        return ValidationNumberPhone(validationNumberPhoneRepository)
    }


    @Provides
    fun provideRequestValidationPassword(): RequestValidationPasswordRepository {
        return RequestValidationPasswordFactory.create()
    }

    @Provides
    fun provideValidationPassword(
        requestValidationPasswordRepository: RequestValidationPasswordRepository
    ): RequestValidationPasswordContract{
        return ValidationPasswordImpl(requestValidationPasswordRepository)
    }
}
