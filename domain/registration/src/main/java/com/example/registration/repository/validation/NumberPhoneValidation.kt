package com.example.registration.repository.validation

interface NumberPhoneValidation {
    suspend fun validationNumberPhone(numberPhone: String): Boolean
}