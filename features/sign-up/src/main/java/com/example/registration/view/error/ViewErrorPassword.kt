package com.example.registration.view.error

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.core.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.ViewModelValidations

class ViewErrorPassword {

    fun viewErrorSecurity(
        viewModelValidations: ViewModelValidations,
        binding: ActivityRegistrationBinding,
        context: Context
    ){
        viewModelValidations.listenerPasswordSecurity.observe(context as LifecycleOwner) { value ->
            with(binding) {
               if (value == false) {
                   errorPassword.visibility = View.VISIBLE
                   errorPassword.text = context.getString(R.string.error_password_security)
               } else {
                   errorPassword.visibility = View.INVISIBLE
               }
            }
        }
    }

    fun viewErrorCharacter(
        viewModelValidations: ViewModelValidations,
        binding: ActivityRegistrationBinding,
        context: Context
    ){
        viewModelValidations.listenerPasswordCharacter.observe(context as LifecycleOwner) { value ->
            with(binding) {
                if (value == false) {
                    errorPassword.visibility = View.VISIBLE
                    errorPassword.text = context.getString(R.string.error_password_character)
                } else {
                    errorPassword.visibility = View.INVISIBLE
                }
            }
        }
    }
}