package com.example.registration.repository.register

interface ValidationNumberPhoneContract {
    suspend fun numberCheck(numberPhoneValidation: String): Boolean
}