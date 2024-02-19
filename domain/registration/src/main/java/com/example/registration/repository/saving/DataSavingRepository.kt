package com.example.registration.repository.saving

import com.example.registration.usecase.SavingData

interface DataSavingRepository {

    suspend fun insert(dataSaving: SavingData)

}