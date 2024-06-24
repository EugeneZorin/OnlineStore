package com.example.registration.viewmodel

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.example.registration.repository.register.RegistrationContract
import com.example.registration.repository.validation.DataValidation
import com.example.registration.repository.validation.NumberPhoneValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val dataValidation: DataValidation,
    private val numberPhoneValidation: NumberPhoneValidation,
    private val registrationContract: RegistrationContract
) : ViewModel() {

    suspend fun savingData(
        numberPhone: String,
        password: String,
        surname: String,
        name: String
    ) {
        registrationContract.registrationImpl(numberPhone, password)
    }

    fun nameValidation(name: String): MutableList<Char> {
        return dataValidation.validationName(name)
    }

    fun firstNameValidation(surname: String): MutableList<Char> {
        return dataValidation.validationSurname(surname)
    }

    suspend fun numberPhoneValidation(number: Editable): Boolean {
        return numberPhoneValidation.validationNumberPhone(number.toString())
    }




}