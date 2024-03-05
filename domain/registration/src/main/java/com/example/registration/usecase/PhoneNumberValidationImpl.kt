package com.example.registration.usecase

import com.example.registration.repository.saving.DataSavingRepository
import com.example.registration.repository.validation.NumberPhoneValidation

class PhoneNumberValidationImpl(
    private val dataSavingRepository: DataSavingRepository
): NumberPhoneValidation {
    override suspend fun validationNumberPhone(numberPhone: String): Boolean {
        return try {
            true
            /*dataSavingRepository.searchNumber(numberPhone = numberPhone)*/
        }catch (e: Exception){
            println("Error in PhoneNumberValidation (validationNumberPhone): ${e.message}")
            false
        }
    }
}