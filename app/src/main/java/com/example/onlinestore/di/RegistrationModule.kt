package com.example.onlinestore.di

import com.example.registration.repository.saving.DataSavingContract
import com.example.registration.repository.saving.DataSavingRepository
import com.example.registration.repository.validation.NumberPhoneValidationContract
import com.example.registration.usecase.PhoneNumberValidation
import com.example.registration.usecase.SavingData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
class RegistrationModule {

    @Provides
    fun bindsPhoneNumberValidation(
        dataSavingRepository: DataSavingRepository
    ): NumberPhoneValidationContract  {
        return PhoneNumberValidation(dataSavingRepository)
    }

    @Provides
    fun bindsSavingData(
        dataSavingRepository: DataSavingRepository
    ): DataSavingContract {
        return SavingData(dataSavingRepository)
    }



}