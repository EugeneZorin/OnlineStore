package com.example.registration.contract

interface RequestValidationPasswordContract {
    suspend fun requestValidationPasswordContract(login: String, password: String): Boolean
}