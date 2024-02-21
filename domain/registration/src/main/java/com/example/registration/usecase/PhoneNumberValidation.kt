package com.example.registration.usecase

import com.example.registration.repository.saving.DataSavingRepository
import com.example.registration.repository.validation.NumberPhoneValidationContract

class PhoneNumberValidation(
    private val dataSavingRepository: DataSavingRepository
): NumberPhoneValidationContract {
    override suspend fun validationNumberPhone(numberPhone: String): Boolean {
        return try {
            dataSavingRepository.searchNumber(numberPhone = numberPhone)
        }catch (e: Exception){
            println("Error in PhoneNumberValidation (validationNumberPhone): ${e.message}")
            false
        }
    }
}