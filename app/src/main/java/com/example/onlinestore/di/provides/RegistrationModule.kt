package com.example.onlinestore.di.provides

import com.example.registration.contract.ContractFormatPhoneNumber
import com.example.registration.contract.RegistrationContract
import com.example.registration.repository.RegistrationRepository
import com.example.registration.usecase.RegistrationFactory
import com.example.registration.usecase.SetFormatPhoneNumberFactory
import com.example.registration.usecase.validation.DataValidationImpl
import com.example.registration.usecase.validation.PasswordValidationImpl
import com.example.registration.validation.DataValidation
import com.example.registration.validation.PasswordValidationContract
import com.example.registration.view.error.ErrorPasswordView
import com.example.registration.view.error.ErrorSymbolsNameSurnameView
import com.example.registration.view.error.contract.ErrorPassword
import com.example.registration.view.error.contract.ErrorSymbolsNameSurname
import dagger.Binds
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
        return SetFormatPhoneNumberFactory.create()
    }


    @Provides
    fun provideValidationPassword(): PasswordValidationContract {
        return PasswordValidationImpl()
    }

    @Provides
    fun providesErrorPasswordView(): ErrorPassword {
        return ErrorPasswordView()
    }

    @Provides
    fun providesErrorSymbolsNameSurnameView(): ErrorSymbolsNameSurname {
        return ErrorSymbolsNameSurnameView()
    }

}

