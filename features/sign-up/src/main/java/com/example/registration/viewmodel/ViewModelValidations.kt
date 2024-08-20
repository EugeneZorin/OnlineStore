package com.example.registration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.contract.ContractFormatPhoneNumber
import com.example.registration.usecase.validation.DataValidationImpl
import com.example.registration.validation.DataValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelValidations @Inject constructor(
    private val validationImpl: DataValidation,
) : ViewModel() {

    private val _listenerSymbolsName: MutableLiveData<List<Char>> = MutableLiveData<List<Char>>()
    val listenerSymbolsName: MutableLiveData<List<Char>> = _listenerSymbolsName

    private val _listenerSymbolsSurname: MutableLiveData<List<Char>> = MutableLiveData<List<Char>>()
    val listenerSymbolsSurname: MutableLiveData<List<Char>> = _listenerSymbolsSurname


    fun validationName(name: String){
        viewModelScope.launch {
            _listenerSymbolsName.value = validationImpl.validationNameSurname(name)
        }
    }

    fun validationSurname(surname: String) {
        viewModelScope.launch {
            _listenerSymbolsSurname.value = validationImpl.validationNameSurname(surname)
        }
    }
}