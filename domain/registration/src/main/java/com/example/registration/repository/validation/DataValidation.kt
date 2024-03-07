package com.example.registration.repository.validation

interface DataValidation {
    fun validationName(name: String): MutableList<Char>
    fun validationFirstName(firstName: String): List<Boolean>

}