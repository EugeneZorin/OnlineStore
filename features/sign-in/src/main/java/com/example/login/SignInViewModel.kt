package com.example.login

import androidx.lifecycle.ViewModel
import com.example.registration.contract.ContractFormatPhoneNumber
import com.example.registration.validation.PasswordValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val contractFormatPhoneNumber: ContractFormatPhoneNumber,
    private val passwordValidation: PasswordValidation,
): ViewModel() {

    fun setFormatNumberPhone(number: CharSequence?): CharSequence {
        return contractFormatPhoneNumber.formatPhoneNumber(number)
    }

    suspend fun passwordValidationCharacter(password: String): Boolean {
        return passwordValidation.validationPasswordCharacter(password)
    }

    suspend fun passwordValidationSecurity(password: String): Boolean {
        return passwordValidation.validationPasswordSecurity(password)
    }
}