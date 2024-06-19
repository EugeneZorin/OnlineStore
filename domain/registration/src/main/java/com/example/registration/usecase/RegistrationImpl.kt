package com.example.registration.usecase

import com.example.registration.repository.register.RegistrationContract
import com.example.registration.repository.register.RegistrationRepository

class RegistrationImpl(
    private val registrationRepository: RegistrationRepository
) : RegistrationContract {

    override suspend fun registrationImpl(
        numberPhone: String,
        password: String
    ): Boolean {
        return try {
            registrationRepository.registration(numberPhone, password)
            true
        } catch (e: Exception) {
            handleRegistrationError(e)
            false
        }
    }

    private fun handleRegistrationError(e: Exception) {
        println("Error RegistrationImpl: ${e.message}")
    }
}