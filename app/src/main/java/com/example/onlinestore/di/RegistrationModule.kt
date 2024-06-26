package com.example.onlinestore.di

import com.example.data.accounts.Registration
import com.example.registration.repository.register.RegistrationContract
import com.example.registration.repository.register.RegistrationRepository
import com.example.registration.repository.validation.DataValidation
import com.example.registration.repository.validation.PasswordValidation
import com.example.registration.usecase.RegistrationImpl
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
        return RegistrationImpl(registrationRepository)
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