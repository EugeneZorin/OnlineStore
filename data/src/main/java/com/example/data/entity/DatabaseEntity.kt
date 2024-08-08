package com.example.data.entity

data class DatabaseEntity(

    val name: String = "name",
    val surname: String = "surname",
    val email: String = "email",
    val password: String = "password",


    val accountDatabase: String = "users",

    val itemDatabase: String = "items",

    val imageDatabase: String = "imageDatabase"
)
