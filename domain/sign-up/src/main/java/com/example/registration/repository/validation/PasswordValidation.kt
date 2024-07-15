package com.example.registration.repository.validation

interface PasswordValidation {
    suspend fun validationPasswordCharacter(password: String): Boolean
    suspend fun validationPasswordSecurity(password: String): Boolean
}