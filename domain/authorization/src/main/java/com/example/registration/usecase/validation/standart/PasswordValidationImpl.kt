package com.example.registration.usecase.validation.standart

import com.example.registration.validation.PasswordValidationContract

class PasswordValidationImpl: PasswordValidationContract {

    override suspend fun validationPasswordCharacter(password: String): Boolean {
        val cyrillicPattern = Regex("\\p{InCyrillic}")
        return !cyrillicPattern.containsMatchIn(password)
    }

    override suspend fun validationPasswordSecurity(password: String): Boolean {
        return password.length >= 10 || password.contains("[a-zA-Z]")
    }


}