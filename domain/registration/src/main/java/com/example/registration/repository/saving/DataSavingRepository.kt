package com.example.registration.repository.saving

import com.example.registration.entities.SavingDataEntity


interface DataSavingRepository {

    suspend fun insert(savingDataEntity: SavingDataEntity)
    suspend fun searchNumber(numberPhone: String): Boolean
    suspend fun update(savingDataEntity: SavingDataEntity)
    suspend fun delete(savingDataEntity: SavingDataEntity)

}