package com.example.registration.presentation

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.example.registration.entities.SavingDataEntity
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


    suspend fun savingData(
        name: String,
        surname: String,
        number: String
    ){
        dataSaving.savingAllData(
            data = SavingDataEntity(
                name = name,
                surname = surname,
                numberPhone = number
            )
        )
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