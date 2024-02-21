package com.example.registration.repository.validation

interface NumberPhoneValidationContract {
    suspend fun validationNumberPhone(numberPhone: String): Boolean
}