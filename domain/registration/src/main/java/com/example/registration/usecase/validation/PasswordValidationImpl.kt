package com.example.registration.usecase.validation

import com.example.registration.repository.validation.PasswordValidation

class PasswordValidationImpl: PasswordValidation {
    override suspend fun validationPassword(password: String): Boolean {
        val cyrillicPattern = Regex("\\p{InCyrillic}")
        return !cyrillicPattern.containsMatchIn(password)
    }
}