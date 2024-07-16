package com.example.registration.contract

interface ValidationNumberPhoneContract {
    suspend fun numberCheck(numberPhoneValidation: String): Boolean
}