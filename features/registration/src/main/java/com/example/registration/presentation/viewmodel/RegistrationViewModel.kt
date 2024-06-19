package com.example.registration.presentation.viewmodel

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.example.registration.entities.SavingDataEntity
import com.example.registration.repository.register.RegistrationContract
import com.example.registration.repository.saving.DataSaving
import com.example.registration.repository.validation.DataValidation
import com.example.registration.repository.validation.NumberPhoneValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val dataValidation: DataValidation,
    private val numberPhoneValidation: NumberPhoneValidation,
    private val dataSaving: DataSaving,
    private val registrationContract: RegistrationContract
) : ViewModel() {

    suspend fun savingData(name: String, surname: String, number: String) {
        registrationContract.registrationImpl(number, name)
    }

    /*suspend fun savingData(name: String, surname: String, number: String) {
        dataSaving.savingAllData(
            data = SavingDataEntity(
                name = name,
                surname = surname,
                numberPhone = number
            )
        )
    }*/

    fun nameValidation(name: String): MutableList<Char> {
        return dataValidation.validationName(name)
    }

    fun firstNameValidation(surname: String): MutableList<Char> {
        return dataValidation.validationSurname(surname)
    }

    suspend fun numberPhoneValidation(number: Editable): Boolean {
        return numberPhoneValidation.validationNumberPhone(number.toString())
    }

    fun formatPhoneNumber(input: CharSequence?): CharSequence {

        val formattedPhone = StringBuilder()

        val digitsOnly = input.toString().replace("\\D".toRegex(), "")

        for (i in digitsOnly.indices) {
            when (i) {
                0 -> {
                    if (digitsOnly[i] != '7') {
                        formattedPhone.append("+ 7 ")
                    } else {
                        formattedPhone.append("+ ")
                    }
                }

                1, 4, 7, 9, 12 -> formattedPhone.append(" ")
            }
            formattedPhone.append(digitsOnly[i])
        }

        return formattedPhone
    }


}