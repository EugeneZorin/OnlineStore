package com.example.registration.error.contract

import android.content.Context
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.ViewModel

interface ErrorPassword {

    fun errorPasswordHolder(
        viewModel: ViewModel,
        binding: ActivityRegistrationBinding,
        context: Context
    )

}