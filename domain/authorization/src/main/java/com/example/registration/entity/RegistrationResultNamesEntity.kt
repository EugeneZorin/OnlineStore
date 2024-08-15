package com.example.registration.entity

data class RegistrationResultNamesEntity(

    val errorCreateUser: String = "The email address is already in use by another account.",

    val numberErrorCreateUser: String = "ERROR_NUMBER_PHONE",
    val numberCreateAccount: String = "Account creation successful",

)
