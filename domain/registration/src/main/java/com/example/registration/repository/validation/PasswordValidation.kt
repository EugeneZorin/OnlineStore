package com.example.registration.repository.validation

interface PasswordValidation {
    suspend fun validationNumberPhone(password: String): Boolean
}