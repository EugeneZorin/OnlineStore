package com.example.registration.usecase.validation.request

import com.example.registration.contract.ValidationNumberPhoneContract
import com.example.registration.repository.ValidationNumberPhoneRepository

class ValidationNumberPhone(
    private val validationNumberPhoneRepository: ValidationNumberPhoneRepository
): ValidationNumberPhoneContract {

    override suspend fun numberCheck(numberPhoneValidation: String): Boolean {
        return validationNumberPhoneRepository.numberCheck(numberPhoneValidation)
    }
}