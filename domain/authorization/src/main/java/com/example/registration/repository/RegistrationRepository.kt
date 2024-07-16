package com.example.registration.repository

interface RegistrationRepository {
    suspend fun registration(
        name: String,
        surname: String,
        numberPhone: String,
        password: String
    )
}