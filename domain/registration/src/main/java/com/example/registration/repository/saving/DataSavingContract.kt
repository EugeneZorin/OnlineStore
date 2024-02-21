package com.example.registration.repository.saving

import com.example.registration.entities.SavingDataEntity

interface DataSavingContract {
    suspend fun savingAllData(data: SavingDataEntity): Boolean
}