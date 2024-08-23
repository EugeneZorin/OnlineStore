package com.example.registration.viewmodel

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

    fun validationName(name: String){
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
            _listenerPasswordCharacter.value = passwordValidation.validationPasswordSecurity(password)
        }
    }

    fun validationPasswordSecurity(password: String) {
        viewModelScope.launch {
            _listenerPasswordSecurity.value = passwordValidation.validationPasswordSecurity(password)
        }
    }
}