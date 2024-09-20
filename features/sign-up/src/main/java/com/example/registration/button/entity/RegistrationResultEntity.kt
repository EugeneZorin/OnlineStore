package com.example.registration.entity

data class RegistrationResultEntity(
    val errorCreateUser: String = "The email address is already in use by another account.",
    val createAccount: String = "Account creation successful",
)
