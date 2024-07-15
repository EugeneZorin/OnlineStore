package com.example.registration.repository.validation

interface DataValidation {
    fun validationNameSymbols(name: String): MutableList<Char>
    fun validationSurnameSymbols(surname: String): MutableList<Char>

    fun validationLengths(value: Int): Boolean


}