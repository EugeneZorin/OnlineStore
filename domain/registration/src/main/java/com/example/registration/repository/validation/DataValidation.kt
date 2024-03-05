package com.example.registration.repository.validation

interface DataValidation {
    fun validationName(name: String): List<Boolean>
    fun validationFirstName(firstName: String): List<Boolean>

}