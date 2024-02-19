package com.example.registration.repository.saving

import com.example.registration.usecase.SavingData

interface DataSavingContract {
    suspend fun validationName(data: SavingData): Boolean
}