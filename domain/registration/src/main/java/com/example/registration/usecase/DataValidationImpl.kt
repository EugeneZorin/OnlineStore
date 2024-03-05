package com.example.registration.usecase

import com.example.registration.repository.validation.DataValidation

class DataValidationImpl:
    DataValidation {

    private var regex = '\u0400'..'\u04FF'

    override fun validationName(name: String): List<Boolean> {
        return try {
            val result = mutableListOf<Boolean>()
            for (char in name) {
                val isCyrillic = char in regex
                result.add(isCyrillic)
            }
            return result
        } catch (e : Exception){
            println("Error in DataValidation (validationName): ${e.message}")
            listOf(false)
        }
    }

    override fun validationFirstName(firstName: String): List<Boolean> {
        return try {
            val result = mutableListOf<Boolean>()
            for (char in firstName) {
                val isCyrillic = char in regex
                result.add(isCyrillic)
            }
            return result
        } catch (e : Exception) {
            println("Error in DataValidation (validationFirstName): ${e.message}")
            listOf(false)
        }
    }


}