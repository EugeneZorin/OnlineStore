package com.example.registration.repository

interface ValidationNumberPhoneRepository {
    suspend fun numberCheck(numberPhoneValidation: String): Boolean
}