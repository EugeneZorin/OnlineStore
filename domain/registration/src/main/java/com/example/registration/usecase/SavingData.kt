package com.example.registration.usecase

import com.example.registration.repository.saving.DataSavingContract
import com.example.registration.repository.saving.DataSavingRepository

class SavingData(
    private val dataSavingRepository: DataSavingRepository
) : DataSavingContract {
    override suspend fun validationName(data: SavingData): Boolean {
        return try {
            dataSavingRepository.insert(data)
            true
        } catch (e: Exception){
            println("Error in SavingData: ${e.message}")
            false
        }
    }

}