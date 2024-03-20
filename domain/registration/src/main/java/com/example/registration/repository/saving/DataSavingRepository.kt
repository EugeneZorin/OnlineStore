package com.example.registration.repository.saving

import com.example.registration.entities.SavingDataEntity


interface DataSavingRepository {

    suspend fun insert(savingDataEntity: SavingDataEntity)
    suspend fun delete(number: String)
    suspend fun searchNumber(numberPhone: String): Boolean
    suspend fun getUser(number: String): SavingDataEntity

}