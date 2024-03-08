package com.example.registration.presentation

import androidx.lifecycle.ViewModel
import com.example.registration.repository.saving.DataSaving
import com.example.registration.repository.validation.DataValidation
import com.example.registration.repository.validation.NumberPhoneValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val dataValidation: DataValidation,
    private val numberPhoneValidation: NumberPhoneValidation,
    private val dataSaving: DataSaving
): ViewModel() {

    fun nameValidation(name: String): MutableList<Char>{
        return dataValidation.validationName(name)
    }
    fun firstNameValidation(firstName: String): MutableList<Char>{
        return dataValidation.validationFirstName(firstName)
    }

    suspend fun numberPhoneValidation(number: String): Boolean{
        return numberPhoneValidation.validationNumberPhone(number)
    }




}

