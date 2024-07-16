package com.example.login

import androidx.lifecycle.ViewModel
import com.example.registration.contract.ContractFormatPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val contractFormatPhoneNumber: ContractFormatPhoneNumber
): ViewModel() {

    fun setFormatNumberPhone(number: CharSequence?): CharSequence {
        return contractFormatPhoneNumber.formatPhoneNumber(number)
    }
}