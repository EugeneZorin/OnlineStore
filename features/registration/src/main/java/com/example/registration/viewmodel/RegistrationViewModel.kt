package com.example.registration.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.entity.EntityRegistrations
import com.example.registration.repository.register.RegistrationContract
import com.example.registration.repository.validation.DataValidation
import com.example.registration.repository.validation.PasswordValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val dataValidation: DataValidation,
    private val registrationContract: RegistrationContract,
    private val passwordValidation: PasswordValidation,
) : ViewModel() {



    private val _listener: MutableLiveData<MutableList<Boolean>> =
        MutableLiveData<MutableList<Boolean>>().apply { value = mutableListOf(false, false, false, false) }
    val listener: LiveData<MutableList<Boolean>> get() = _listener

    private val _accountDetails: MutableLiveData<MutableList<String>> =
        MutableLiveData<MutableList<String>>().apply { value = mutableListOf("", "", "", "") }


    fun savingData() {
        viewModelScope.launch {
            registrationContract.registrationImpl(
                _accountDetails.value!![0],
                _accountDetails.value!![1],
                _accountDetails.value!![2],
                _accountDetails.value!![3]
            )
        }
    }

    fun nameValidation(name: String): MutableList<Char> {
        val result = dataValidation.validationName(name)
        updateListener(0, result.isEmpty())
        updateAccountDetails(0, name, result.isEmpty())
        return result
    }

    fun surnameValidation(surname: String): MutableList<Char> {
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

    suspend fun passwordValidation(number: String): Boolean {
         val result = passwordValidation.validationPassword(number)
         updateListener(3, result)
         updateAccountDetails(3, number, result)
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