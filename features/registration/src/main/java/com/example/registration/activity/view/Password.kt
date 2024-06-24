package com.example.registration.activity.view

import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.RegistrationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Password {

    suspend fun password(
        registrationViewModel: RegistrationViewModel,
        binding: ActivityRegistrationBinding
    ): Boolean {
        return withContext(Dispatchers.IO) {
            registrationViewModel.passwordValidation(binding.password.text.toString())
        }
    }
}