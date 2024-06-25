package com.example.registration.repository.register

interface RegistrationRepository {
    suspend fun registration(numberPhone: String, password: String, name: String, surname: String)
}