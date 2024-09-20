package com.example.data.entity

data class DatabaseEntity(

    val name: String = "name",
    val surname: String = "surname",
    val email: String = "email",
    val password: String = "password",
    val accountDatabase: String = "users",
    val itemDatabase: String = "items",
    val imageDatabase: String = "imageDatabase",

    // Create an account
    val errorCreateUser: String = "The email address is already in use by another account.",
    val createAccounts: String = "create_user_accounts",
    val setDataAccounts: String = "set_data_user",

    val databaseAccounts: String = "users"
)
