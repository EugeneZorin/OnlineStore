package com.example.registration.repository.validation

interface DataValidationContract {
    fun validationName(name: String): Boolean
    fun validationFirstName(firstName: String): Boolean

}