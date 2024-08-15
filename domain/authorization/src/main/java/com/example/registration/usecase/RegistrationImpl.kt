package com.example.registration.usecase

import com.example.registration.contract.RegistrationContract
import com.example.registration.repository.RegistrationRepository

private class RegistrationImpl(
    private val registrationRepository: RegistrationRepository
) : RegistrationContract {

    override suspend fun registrationImpl(
        name: String,
        surname: String,
        numberPhone: String,
        password: String
    ): String? {
        return try {
            registrationRepository.registration(name, surname, numberPhone, password)

        } catch (e: Exception) {
            handleRegistrationError(e)
            e.message
        }
    }

    private fun handleRegistrationError(e: Exception) {
        println("Error RegistrationImpl: ${e.message}")
    }
}

object RegistrationFactory {
    fun create(registrationRepository: RegistrationRepository): RegistrationContract {
        return RegistrationImpl(registrationRepository)
    }
}