package com.example.registration.error.contract

import android.content.Context
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.ViewModel

interface ErrorNameSurname {
    /*fun viewErrorSymbolsName(viewModel: ViewModel, binding: ActivityRegistrationBinding, context: Context)
    fun viewErrorSymbolsSurname(viewModel: ViewModel, binding: ActivityRegistrationBinding, context: Context)
    fun viewErrorLengthsNameSurname(viewModel: ViewModel, binding: ActivityRegistrationBinding, context: Context)*/
    fun errorNameSurnameHolder(
        viewModel: ViewModel,
        binding: ActivityRegistrationBinding,
        context: Context
    )
}