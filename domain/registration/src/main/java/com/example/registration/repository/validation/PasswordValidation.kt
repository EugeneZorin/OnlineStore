package com.example.registration.repository.validation

interface PasswordValidation {
    suspend fun validationPassword(password: String): Boolean
}