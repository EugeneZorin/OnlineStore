package com.example.registration.validation

interface PasswordValidationContract {
    suspend fun validationPasswordCharacter(password: String): Boolean
    suspend fun validationPasswordSecurity(password: String): Boolean
}