package com.example.registration.repository.validation

import java.util.regex.Pattern

interface DataValidationContract {
    fun validationName(name: String): Boolean
    fun validationFirstName(firstName: String): Boolean

}