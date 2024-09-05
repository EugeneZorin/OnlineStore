package com.example.registration.error.contract

import android.content.Context
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.ViewModelValidations

interface ErrorSymbolsNameSurname {
    fun viewErrorSymbolsName(viewModelValidations: ViewModelValidations, binding: ActivityRegistrationBinding, context: Context)
    fun viewErrorSymbolsSurname(viewModelValidations: ViewModelValidations, binding: ActivityRegistrationBinding, context: Context)
}