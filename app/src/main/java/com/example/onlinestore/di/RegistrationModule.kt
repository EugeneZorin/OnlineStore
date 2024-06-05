package com.example.onlinestore.di

import android.app.Application
import com.example.data.accounts.room.UserDao
import com.example.data.accounts.room.UserDatabase
import com.example.data.accounts.usecase.RegistrationImpl
import com.example.registration.repository.saving.DataSaving
import com.example.registration.repository.saving.DataSavingRepository
import com.example.registration.repository.validation.DataValidation
import com.example.registration.repository.validation.NumberPhoneValidation
import com.example.registration.usecase.DataValidationImpl
import com.example.registration.usecase.PhoneNumberValidationImpl
import com.example.registration.usecase.SavingDataImpl
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
    fun provideRegistration(
        userDao: UserDao
    ): DataSavingRepository {
        return RegistrationImpl(userDao)
    }


    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }


    @Provides
    fun provideUserDatabase(application: Application): UserDatabase {
        return UserDatabase.database(application)
    }



}