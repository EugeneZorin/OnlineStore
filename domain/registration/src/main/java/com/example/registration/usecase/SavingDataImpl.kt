package com.example.registration.usecase

import com.example.registration.entities.SavingDataEntity
import com.example.registration.repository.saving.DataSaving
import com.example.registration.repository.saving.DataSavingRepository

class SavingDataImpl(
    private val dataSavingRepository: DataSavingRepository
) : DataSaving {
    override suspend fun savingAllData(data: SavingDataEntity): Boolean {
        return try {
            dataSavingRepository.insert(data)
            true
        } catch (e: Exception){
            println("Error in SavingData: ${e.message}")
            false
        }
    }

}