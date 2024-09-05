package com.example.registration.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.contract.ContractFormatPhoneNumber
import com.example.registration.usecase.validation.DataValidationImpl
import com.example.registration.usecase.validation.PasswordValidationImpl
import com.example.registration.validation.DataValidation
import com.example.registration.validation.PasswordValidationContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelValidations @Inject constructor(
    private val dataValidation: DataValidation,
    private val passwordValidation: PasswordValidationContract
) : ViewModel() {

    private val _lengthsName: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    // temporarily
    private val lengthsName: MutableLiveData<Boolean> = _lengthsName

    private val _lengthsSurname: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    // temporarily
    private val lengthsSurname: MutableLiveData<Boolean> = _lengthsSurname

    private val _listenerSymbolsName: MutableLiveData<List<Char>> = MutableLiveData<List<Char>>()
    val listenerSymbolsName: MutableLiveData<List<Char>> = _listenerSymbolsName

    private val _listenerSymbolsSurname: MutableLiveData<List<Char>> = MutableLiveData<List<Char>>()
    val listenerSymbolsSurname: MutableLiveData<List<Char>> = _listenerSymbolsSurname

    private val _listenerPasswordCharacter: MutableLiveData<Boolean> = MutableLiveData()
    val listenerPasswordCharacter: MutableLiveData<Boolean> = _listenerPasswordCharacter

    private val _listenerPasswordSecurity: MutableLiveData<Boolean> = MutableLiveData()
    val listenerPasswordSecurity: MutableLiveData<Boolean> = _listenerPasswordSecurity

    private val _listenerFieldCheck: MediatorLiveData<Boolean> = MediatorLiveData(false)
    var listenerFieldCheck: MutableLiveData<Boolean> = _listenerFieldCheck

    private fun mediator() {

        val nameValid = _listenerSymbolsName.value?.isEmpty() ?: true
        val surnameValid = _listenerSymbolsSurname.value?.isEmpty() ?: true
        val passwordCharacterValid = _listenerPasswordCharacter.value == true
        val passwordSecurityValid = _listenerPasswordSecurity.value == true
        val lengthsName = _lengthsName.value == true
        val lengthsSurname = _lengthsSurname.value == true

        _listenerFieldCheck.value =
            nameValid && surnameValid && passwordCharacterValid && passwordSecurityValid && lengthsName && lengthsSurname
    }

    init {
        _listenerFieldCheck.addSource(_listenerSymbolsName) { mediator() }
        _listenerFieldCheck.addSource(_listenerSymbolsSurname) { mediator() }
        _listenerFieldCheck.addSource(_listenerPasswordCharacter) { mediator() }
        _listenerFieldCheck.addSource(_listenerPasswordSecurity) { mediator() }
        _listenerFieldCheck.addSource(_lengthsName) { mediator() }
        _listenerFieldCheck.addSource(_lengthsSurname) { mediator() }
    }


    fun validationName(name: String) {
        viewModelScope.launch {
            _listenerSymbolsName.value = dataValidation.validationNameSurname(name)
            lengthsName.value = dataValidation.validationLengths(name)

        }
    }

    fun validationSurname(surname: String) {
        viewModelScope.launch {
            _listenerSymbolsSurname.value = dataValidation.validationNameSurname(surname)
            lengthsSurname.value = dataValidation.validationLengths(surname)
        }
    }

    fun validationPasswordCharacter(password: String) {
        viewModelScope.launch {
            _listenerPasswordCharacter.value =
                passwordValidation.validationPasswordCharacter(password)
        }
    }

    fun validationPasswordSecurity(password: String) {
        viewModelScope.launch {
            _listenerPasswordSecurity.value =
                passwordValidation.validationPasswordSecurity(password)
        }
    }


}