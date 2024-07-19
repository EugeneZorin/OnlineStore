package com.example.registration.repository

interface RequestValidationPasswordRepository {
    suspend fun requestValidationPassword(login: String, password: String): Boolean
}