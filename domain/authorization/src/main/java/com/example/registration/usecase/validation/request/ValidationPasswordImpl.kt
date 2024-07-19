package com.example.registration.usecase.validation.request

import com.example.registration.contract.RequestValidationPasswordContract
import com.example.registration.repository.RequestValidationPasswordRepository

class ValidationPasswordImpl(
    private val requestValidationPasswordRepository: RequestValidationPasswordRepository
) : RequestValidationPasswordContract {

    override suspend fun requestValidationPasswordContract(
        login: String,
        password: String
    ): Boolean {
        return try {
            requestValidationPasswordRepository.requestValidationPassword(login, password)
        } catch (e: Exception) {
            println("Error ValidationPasswordImpl: ${e.message}")
            false
        }
    }

}