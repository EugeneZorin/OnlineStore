package com.example.registration.view

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.core.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.ViewModelValidations

class ViewErrorSymbolsSurname {

    fun viewErrorSymbolsSurname(
        viewModelValidations: ViewModelValidations,
        binding: ActivityRegistrationBinding,
        context: Context
    ) {
        viewModelValidations.listenerSymbolsSurname.observe(context as LifecycleOwner) { value ->
            with(binding) {
                if (value.isNotEmpty()) {
                    errorMessageSurname.visibility = View.VISIBLE
                    errorMessageSurname.text = context.getText(R.string.error_invalid_char)
                } else {
                    errorMessageSurname.visibility = View.INVISIBLE
                }
            }
        }
    }
}