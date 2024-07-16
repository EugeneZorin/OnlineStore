package com.example.registration.validation

interface PasswordValidation {
    suspend fun validationPasswordCharacter(password: String): Boolean
    suspend fun validationPasswordSecurity(password: String): Boolean
}