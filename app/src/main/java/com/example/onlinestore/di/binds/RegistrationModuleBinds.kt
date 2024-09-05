package com.example.onlinestore.di.binds

import com.example.data.accounts.Registration
import com.example.registration.repository.RegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RegistrationModuleBinds{

    @Binds
    abstract fun bindsRegistration(registration: Registration): RegistrationRepository




}