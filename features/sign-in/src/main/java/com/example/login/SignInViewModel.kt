package com.example.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.registration.contract.ContractFormatPhoneNumber
import com.example.registration.contract.RequestValidationPasswordContract
import com.example.registration.contract.ValidationNumberPhoneContract
import com.example.registration.validation.PasswordValidationContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val contractFormatPhoneNumber: ContractFormatPhoneNumber,
    private val passwordValidationContract: PasswordValidationContract,
    private val validationNumberPhoneContract: ValidationNumberPhoneContract,
    private val requestValidationPasswordContract: RequestValidationPasswordContract
) : ViewModel() {

    private val _dataCustodian: MutableLiveData<MutableList<Boolean>> =
        MutableLiveData<MutableList<Boolean>>().apply {
            value = mutableListOf(false, false, false)
        }

    val dataCustodian: MutableLiveData<MutableList<Boolean>>
        get() = _dataCustodian

    fun setFormatNumberPhone(number: CharSequence?): CharSequence {
        return contractFormatPhoneNumber.formatPhoneNumber(number)
    }


    suspend fun passwordValidationCharacter(password: String): Boolean {
        val result = passwordValidationContract.validationPasswordCharacter(password)
        updateDataCustodian(0, result)
        return result
    }

    suspend fun passwordValidationSecurity(password: String): Boolean {
        val result = passwordValidationContract.validationPasswordSecurity(password)
        updateDataCustodian(1, result)
        return result
    }

    fun numberCharactersPhoneNumber(number: CharSequence?) {
        if (number?.length!! >= 17) {
            updateDataCustodian(2, true)
        } else {
            updateDataCustodian(2, false)
        }
    }

    private fun updateDataCustodian(index: Int, value: Boolean) {
        _dataCustodian.value = _dataCustodian.value?.toMutableList()?.apply {
            this[index] = value
        }
    }


    suspend fun validationNumberPhone(number: String): Boolean {
        return validationNumberPhoneContract.numberCheck(number)
    }

    suspend fun requestValidationPassword(login: String, password: String): Boolean {
        return requestValidationPasswordContract.requestValidationPasswordContract(login, password)
    }

}