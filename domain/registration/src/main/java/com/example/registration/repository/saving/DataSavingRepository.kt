package com.example.registration.repository.saving

import com.example.registration.entities.SavingDataEntity


interface DataSavingRepository {

    suspend fun insert(savingDataEntity: SavingDataEntity)
    suspend fun delete(id: String)
    suspend fun searchNumber(numberPhone: String): Boolean



}