package com.example.registration.usecase.validation

import com.example.registration.repository.register.ValidationNumberPhoneContract
import com.example.registration.repository.register.ValidationNumberPhoneRepository

class ValidationNumberPhone(
    private val validationNumberPhoneRepository: ValidationNumberPhoneRepository
): ValidationNumberPhoneContract {

    override suspend fun numberCheck(numberPhoneValidation: String): Boolean {
        return validationNumberPhoneRepository.numberCheck(numberPhoneValidation)
    }
}