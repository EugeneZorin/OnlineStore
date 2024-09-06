package com.example.registration.error

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.core.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.error.contract.ErrorPassword
import com.example.registration.viewmodel.ViewModel
import javax.inject.Inject

class ErrorPasswordView @Inject constructor(): ErrorPassword {

    override fun errorPasswordHolder(
        viewModel: ViewModel,
        binding: ActivityRegistrationBinding,
        context: Context
    ){

        viewModel.listenerPasswordSecurity.observe(context as LifecycleOwner) { securityValid ->
            val characterValid = viewModel.listenerPasswordCharacter.value ?: true
            updatePasswordError(binding, context, securityValid, characterValid)
        }

        viewModel.listenerPasswordCharacter.observe(context as LifecycleOwner) { characterValid ->
            val securityValid = viewModel.listenerPasswordSecurity.value ?: true
            updatePasswordError(binding, context, securityValid, characterValid)
        }

    }

    private fun updatePasswordError(
        binding: ActivityRegistrationBinding,
        context: Context,
        securityValid: Boolean,
        characterValid: Boolean
    ){
       with(binding.errorPassword) {
           if (!securityValid) {
               visibility = View.VISIBLE
               text = context.getString(R.string.error_password_security)
           } else if (!characterValid) {
               visibility = View.VISIBLE
               text = context.getString(R.string.error_password_character)
           } else {
               visibility = View.INVISIBLE
           }
       }
    }
}