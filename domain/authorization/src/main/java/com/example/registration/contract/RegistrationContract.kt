package com.example.registration.contract

interface RegistrationContract {
    suspend fun registrationImpl(
        name: String,
        surname: String,
        numberPhone: String,
        password: String
    ): String?
}