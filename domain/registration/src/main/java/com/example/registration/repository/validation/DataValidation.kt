package com.example.registration.repository.validation

interface DataValidation {
    fun validationName(name: String): MutableList<Char>
    fun validationSurname(surname: String): MutableList<Char>

}