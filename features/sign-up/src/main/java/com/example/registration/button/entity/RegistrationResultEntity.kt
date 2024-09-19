package com.example.registration.entity

data class RegistrationResultEntity(

    val errorCreateUser: String = "The email address is already in use by another account.",
    val numberErrorCreateUser: String = "ERROR_NUMBER_PHONE",
    val createAccount: String = "Account creation successful",

)
