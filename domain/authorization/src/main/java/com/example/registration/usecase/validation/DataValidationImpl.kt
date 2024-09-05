package com.example.registration.usecase.validation

import com.example.registration.validation.DataValidation

class DataValidationImpl: DataValidation {

    private var regex = '\u0400'..'\u04FF'

    override fun validationNameSurname(name: String): MutableList<Char> {
        return try {
            val result = mutableListOf<Char>()
            for (char in name) {
                when (char in regex){
                    false -> result.add(char)
                    else -> {}
                }
            }
            return result

        } catch (e : Exception){
            println("Error in DataValidation (validationNameSurname): ${e.message}")
            mutableListOf()
        }
    }

    override fun validationLengths(value: String): Boolean {
        return value.length >= 2
    }

}