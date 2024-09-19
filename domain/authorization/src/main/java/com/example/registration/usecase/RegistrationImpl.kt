package com.example.registration.usecase

import com.example.registration.contract.RegistrationContract
import com.example.registration.entity.RegistrationResultNamesEntity
import com.example.registration.repository.RegistrationRepository

private class RegistrationImpl(
    private val registrationRepository: RegistrationRepository
) : RegistrationContract {

    private val registrationResultNamesEntity: RegistrationResultNamesEntity =
        RegistrationResultNamesEntity()

    override suspend fun registrationImpl(
        name: String,
        surname: String,
        numberPhone: String,
        password: String
    ): String {
        val result = registrationRepository.registration(name, surname, numberPhone, password)
        println(result)
        return when (result) {
            registrationResultNamesEntity.errorCreateUser -> {
                registrationResultNamesEntity.numberErrorCreateUser
            }

            registrationResultNamesEntity.numberCreateAccount -> {
                registrationResultNamesEntity.numberCreateAccount
            }

            else -> {
                registrationResultNamesEntity.errorCreateUser
            }
        }
    }

}

object RegistrationFactory {
    fun create(registrationRepository: RegistrationRepository): RegistrationContract {
        return RegistrationImpl(registrationRepository)
    }
}