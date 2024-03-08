package com.example.data

import com.example.registration.entities.SavingDataEntity
import com.example.registration.repository.saving.DataSavingRepository

class RegistrationImpl: DataSavingRepository {
    override suspend fun insert(savingDataEntity: SavingDataEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun searchNumber(numberPhone: String): Boolean {
        return true
    }

    override suspend fun update(savingDataEntity: SavingDataEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(savingDataEntity: SavingDataEntity) {
        TODO("Not yet implemented")
    }
}