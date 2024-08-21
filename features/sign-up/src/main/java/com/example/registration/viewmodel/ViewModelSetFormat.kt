package com.example.registration.viewmodel

import androidx.lifecycle.ViewModel
import com.example.registration.contract.ContractFormatPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelSetFormat @Inject constructor(
    private val contractFormatPhoneNumber: ContractFormatPhoneNumber
): ViewModel() {

    fun setFormatNumberPhone(number: String): CharSequence {
        return contractFormatPhoneNumber.formatPhoneNumber(number)
    }
}