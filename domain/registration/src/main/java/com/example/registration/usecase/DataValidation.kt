package com.example.registration.usecase

import com.example.registration.repository.validation.DataValidationContract
import java.util.regex.Pattern

class DataValidation: DataValidationContract {

    private var regex = "[а-яёА-ЯЁ]+"
    private val pattern: Pattern = Pattern.compile(regex)

    override fun validationName(name: String): Boolean {
        return try {
            pattern.matcher(name).find()
        } catch (e : Exception){
            println("Error in DataValidation (validationName): ${e.message}")
            false
        }
    }

    override fun validationFirstName(firstName: String): Boolean {
        return try {
            pattern.matcher(firstName).find()
        } catch (e : Exception){
            println("Error in DataValidation (validationFirstName): ${e.message}")
            false
        }
    }


}