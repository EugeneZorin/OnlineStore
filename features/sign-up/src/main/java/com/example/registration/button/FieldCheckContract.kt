package com.example.registration.button

import android.content.Context
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.ViewModelValidations

interface FieldCheckContract {
    fun fieldCheck(
        viewModelValidations: ViewModelValidations,
        binding: ActivityRegistrationBinding,
        context: Context
    )
}