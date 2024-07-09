package com.example.registration.repository.register

interface ValidationNumberPhoneRepository {
    suspend fun numberCheck(numberPhoneValidation: String): Boolean
}