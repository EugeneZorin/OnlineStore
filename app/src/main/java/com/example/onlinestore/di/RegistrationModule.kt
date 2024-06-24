package com.example.onlinestore.di

import android.app.Application
import com.example.data.accounts.room.UserDao
import com.example.data.accounts.room.UserDatabase
import com.example.data.accounts.usecase.Registration
import com.example.data.accounts.usecase.RegistrationImplRoom
import com.example.registration.repository.register.RegistrationContract
import com.example.registration.repository.register.RegistrationRepository
import com.example.registration.repository.saving.DataSaving
import com.example.registration.repository.saving.DataSavingRepository
import com.example.registration.repository.validation.DataValidation
import com.example.registration.repository.validation.NumberPhoneValidation
import com.example.registration.repository.validation.PasswordValidation
import com.example.registration.usecase.validation.DataValidationImpl
import com.example.registration.usecase.validation.PhoneNumberValidationImpl
import com.example.registration.usecase.RegistrationImpl
import com.example.registration.usecase.SavingDataImpl
import com.example.registration.usecase.validation.PasswordValidationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RegistrationModule{

    @Provides
    fun providePhoneNumberValidation(
        dataSavingRepository: DataSavingRepository
    ): NumberPhoneValidation {
        return PhoneNumberValidationImpl(dataSavingRepository)
    }

    @Provides
    fun provideSavingData(
        dataSavingRepository: DataSavingRepository
    ): DataSaving {
        return SavingDataImpl(dataSavingRepository)
    }

    @Provides
    fun provideDataValidation(): DataValidation {
        return DataValidationImpl()
    }

    @Provides
    fun provideRegistrationRoom(
        userDao: UserDao
    ): DataSavingRepository {
        return RegistrationImplRoom(userDao)
    }


    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }


    @Provides
    fun provideUserDatabase(application: Application): UserDatabase {
        return UserDatabase.database(application)
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