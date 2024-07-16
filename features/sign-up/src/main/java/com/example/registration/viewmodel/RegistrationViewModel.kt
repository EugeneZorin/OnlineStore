package com.example.registration.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.contract.ContractFormatPhoneNumber
import com.example.registration.contract.RegistrationContract
import com.example.registration.contract.ValidationNumberPhoneContract
import com.example.registration.validation.DataValidation
import com.example.registration.validation.PasswordValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val dataValidation: DataValidation,
    private val registrationContract: RegistrationContract,
    private val passwordValidation: PasswordValidation,
    private val validationNumberPhoneContract: ValidationNumberPhoneContract,
    private val contractFormatPhoneNumber: ContractFormatPhoneNumber
) : ViewModel() {

    private val _listenerNumberPhone: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val listenerNumberPhone: MutableLiveData<Boolean> = _listenerNumberPhone

    private val _listener: MutableLiveData<MutableList<Boolean>> =
        MutableLiveData<MutableList<Boolean>>().apply {
            value = mutableListOf(false, false, false, false, false, false, false)
        }

    val listener: LiveData<MutableList<Boolean>> get() = _listener

    private val _accountDetails: MutableLiveData<MutableList<String>> =
        MutableLiveData<MutableList<String>>().apply { value = mutableListOf("", "", "", "") }


    fun savingData() {
        viewModelScope.launch {
            when (checkingPhoneNumberMatches(_accountDetails.value?.get(2) ?: "")
            ) {
                true -> {
                    listenerNumberPhone.value = true
                    registrationContract.registrationImpl(
                        _accountDetails.value!![0],
                        _accountDetails.value!![1],
                        _accountDetails.value!![2],
                        _accountDetails.value!![3]
                    )
                }

                false -> {
                    listenerNumberPhone.value = false
                }
            }
        }
    }

    fun setFormatNumber(input: CharSequence?): CharSequence {
        return contractFormatPhoneNumber.formatPhoneNumber(input)
    }

    private suspend fun checkingPhoneNumberMatches(number: String): Boolean {
        return validationNumberPhoneContract.numberCheck(number)
    }


    fun nameValidationSymbols(name: String): MutableList<Char> {
        val result = dataValidation.validationNameSymbols(name)
        updateListener(0, result.isEmpty())
        updateAccountDetails(0, name, result.isEmpty())
        return result
    }


    fun surnameValidation(surname: String): MutableList<Char> {
        val result = dataValidation.validationNameSymbols(surname)
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


    suspend fun passwordValidationCharacter(password: String): Boolean {
        val result = passwordValidation.validationPasswordCharacter(password)
        updateListener(3, result)
        updateAccountDetails(3, password, result)
        return result
    }

    suspend fun passwordValidationSecurity(password: String): Boolean {
        val result = passwordValidation.validationPasswordSecurity(password)
        updateListener(4, result)
        return result
    }

    fun validationNameLengths(value: Int): Boolean {
        val result = dataValidation.validationLengths(value)
        updateListener(5, result)
        return result
    }

    fun validationSurnameLengths(value: Int): Boolean {
        val result = dataValidation.validationLengths(value)
        updateListener(6, result)
        return result
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