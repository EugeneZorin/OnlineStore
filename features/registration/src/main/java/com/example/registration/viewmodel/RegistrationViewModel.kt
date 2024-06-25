package com.example.registration.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.registration.repository.register.RegistrationContract
import com.example.registration.repository.validation.DataValidation
import com.example.registration.repository.validation.NumberPhoneValidation
import com.example.registration.repository.validation.PasswordValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val dataValidation: DataValidation,
    private val numberPhoneValidation: NumberPhoneValidation,
    private val registrationContract: RegistrationContract,
    private val passwordValidation: PasswordValidation,
) : ViewModel() {


    private val _listener: MutableLiveData<MutableList<Boolean>> = MutableLiveData<MutableList<Boolean>>().apply { value = mutableListOf(false, false, false) }
    val listener: LiveData<MutableList<Boolean>> get() = _listener

    private val _accountDetails: MutableLiveData<MutableList<String>> =
        MutableLiveData<MutableList<String>>().apply { value = mutableListOf("", "", "") }
    val accountDetails: LiveData<MutableList<String>> get() = _accountDetails


    suspend fun savingData(
        numberPhone: String,
        surname: String,
        name: String
    ) {
        registrationContract.registrationImpl(numberPhone, surname, name)
    }

    fun nameValidation(name: String): MutableList<Char> {
        val result = dataValidation.validationName(name)
        updateListener(0, result.isEmpty())
        updateAccountDetails(0, name, result.isEmpty())
        return result
    }

    fun firstNameValidation(surname: String): MutableList<Char> {
        val result = dataValidation.validationName(surname)
        updateListener(1, result.isEmpty())
        updateAccountDetails(1, surname, result.isEmpty())
        return result
    }

    fun validationLengthNumberPhone(number: String): Boolean {
        val result = number.length >= 17
        updateListener(2, result)
        updateAccountDetails(2, number, result)
        return result
    }

   /* suspend fun numberPhoneValidation(number: Editable): Boolean {
        val result = numberPhoneValidation.validationNumberPhone(number.toString())
        updateListener(2, result)
        return result
    }*/


    suspend fun passwordValidation(password: String): Boolean {
        return passwordValidation.validationNumberPhone(password)
    }



    private fun updateAccountDetails(index: Int, data: String, check: Boolean) {
        if (check) {
            _accountDetails.value = _accountDetails.value?.toMutableList()?.apply {
                this[index] = data
            }
        }
    }


    private fun updateListener(index: Int, value: Boolean) {
        _listener.value = _listener.value?.toMutableList()?.apply {
            this[index] = value
        }
    }
}