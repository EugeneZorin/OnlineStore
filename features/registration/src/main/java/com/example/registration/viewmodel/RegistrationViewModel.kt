package com.example.registration.viewmodel

import android.text.Editable
import android.util.Log
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


    private val _listener: MutableLiveData<MutableList<Boolean>> =
        MutableLiveData<MutableList<Boolean>>().apply { value = mutableListOf(false, false) }
    val listener: LiveData<MutableList<Boolean>> get() = _listener


    suspend fun savingData(
        numberPhone: String,
        password: String,
        surname: String,
        name: String
    ) {
        registrationContract.registrationImpl(numberPhone, password)
    }

    fun nameValidation(name: String): MutableList<Char> {

        val result = dataValidation.validationName(name)
        updateListener(0, result.isEmpty())
        return result
    }

    fun firstNameValidation(surname: String): MutableList<Char> {
        val result = dataValidation.validationName(surname)
        updateListener(1, result.isEmpty())

        return result
    }

    suspend fun numberPhoneValidation(number: Editable): Boolean {
        val result = numberPhoneValidation.validationNumberPhone(number.toString())
        /*updateListener(2, result)*/
        return result
    }

    suspend fun passwordValidation(password: String): Boolean {
        return passwordValidation.validationNumberPhone(password)
    }

    private fun updateListener(index: Int, value: Boolean) {
        _listener.value = _listener.value?.toMutableList()?.apply {
            this[index] = value
        }
    }
}