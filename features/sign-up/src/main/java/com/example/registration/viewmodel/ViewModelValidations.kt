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

    private fun mediator(){
        val nameValid = _listenerSymbolsName.value?.isEmpty() ?: true
        val surnameValid = _listenerSymbolsSurname.value?.isEmpty() ?: true
        val passwordCharacterValid = _listenerPasswordCharacter.value == true
        val passwordSecurityValid = _listenerPasswordSecurity.value == true

        _listenerFieldCheck.value = nameValid && surnameValid && passwordCharacterValid && passwordSecurityValid
    }

    init {
        _listenerFieldCheck.addSource(_listenerSymbolsName) {mediator()}
        _listenerFieldCheck.addSource(_listenerSymbolsSurname) {mediator()}
        _listenerFieldCheck.addSource(_listenerPasswordCharacter) {mediator()}
        _listenerFieldCheck.addSource(_listenerPasswordSecurity) {mediator()}
    }


    fun validationName(name: String) {
        viewModelScope.launch {
            _listenerSymbolsName.value = dataValidation.validationNameSurname(name)

        }
    }

    fun validationSurname(surname: String) {
        viewModelScope.launch {
            _listenerSymbolsSurname.value = dataValidation.validationNameSurname(surname)
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
    init {
        _listenerFieldCheck.value = _listenerSymbolsName.value != null || _listenerSymbolsSurname.value != null || _listenerPasswordSecurity.value == true || _listenerPasswordCharacter.value == true

    }

}