package com.example.onlinestore.di

import com.example.data.accounts.Registration
import com.example.registration.contract.ContractFormatPhoneNumber
import com.example.registration.contract.RegistrationContract
import com.example.registration.repository.RegistrationRepository
import com.example.registration.validation.DataValidation
import com.example.registration.validation.PasswordValidation
import com.example.registration.usecase.RegistrationFactory
import com.example.registration.usecase.SetFormatPhoneNumberImpl
import com.example.registration.usecase.validation.DataValidationImpl
import com.example.registration.usecase.validation.PasswordValidationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RegistrationModule{


    @Provides
    fun provideDataValidation(): DataValidation {
        return DataValidationImpl()
    }


    @Provides
    fun provideRegistrationImpl(
        registrationRepository: RegistrationRepository
    ): RegistrationContract {
        return RegistrationFactory.create(registrationRepository)
    }


    @Provides
    fun provideSetFormatNumber(): ContractFormatPhoneNumber {
        return SetFormatPhoneNumberImpl()
    }


    @Provides
    fun provideValidationPassword(): PasswordValidation {
        return PasswordValidationImpl()
    }

    @Provides
    fun provideRegistration(): RegistrationRepository {
        return Registration()
    }



}