package com.example.registration.validation

interface DataValidation {
    fun validationNameSurname(name: String): MutableList<Char>
    fun validationLengths(value: String): Boolean
}