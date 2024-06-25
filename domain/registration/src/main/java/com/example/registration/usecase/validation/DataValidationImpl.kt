package com.example.registration.usecase.validation

import com.example.registration.repository.validation.DataValidation

class DataValidationImpl: DataValidation {

    private var regex = '\u0400'..'\u04FF'

    override fun validationName(name: String): MutableList<Char> {
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
            println("Error in DataValidation (validationName): ${e.message}")
            mutableListOf()
        }
    }




    override fun validationSurname(surname: String): MutableList<Char> {
        return try {
            val result = mutableListOf<Char>()
            for (char in surname) {
                when (char in regex){
                    false -> result.add(char)
                    else -> {}
                }
            }
            return result

        } catch (e : Exception){
            println("Error in DataValidation (validationSurname): ${e.message}")
            mutableListOf()
        }
    }




}