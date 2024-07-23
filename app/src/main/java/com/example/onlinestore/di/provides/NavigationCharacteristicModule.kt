package com.example.onlinestore.di.provides

import android.content.Context
import com.example.catalog.contract.NavigationCharacteristic
import com.example.characteristic.handler.CharacteristicHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext


@Module
@InstallIn(ActivityComponent::class)
object NavigationCharacteristicModule {
    @Provides
    fun provideCharacteristicHandler(@ActivityContext context: Context): NavigationCharacteristic {
        return CharacteristicHandler(context)
    }
}