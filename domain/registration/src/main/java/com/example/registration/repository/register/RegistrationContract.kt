package com.example.registration.repository.register

interface RegistrationContract {
    suspend fun registrationImpl(numberPhone: String, password: String, name: String): Boolean
}