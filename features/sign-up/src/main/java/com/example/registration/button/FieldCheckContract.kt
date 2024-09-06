package com.example.registration.button

import android.content.Context
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.ViewModel

interface FieldCheckContract {
    fun fieldCheck(
        viewModel: ViewModel,
        binding: ActivityRegistrationBinding,
        context: Context
    )
}