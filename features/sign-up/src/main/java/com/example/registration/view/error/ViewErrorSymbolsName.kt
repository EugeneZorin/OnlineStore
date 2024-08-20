package com.example.registration.view.error

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.core.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.ViewModelValidations

class ViewErrorSymbolsName{

    fun viewErrorSymbolsName(
        viewModelValidations: ViewModelValidations,
        binding: ActivityRegistrationBinding,
        context: Context
    ){
        viewModelValidations.listenerSymbolsName.observe(context as LifecycleOwner) { value ->
            with(binding) {
                if (value.isNotEmpty()) {
                    errorMessageName.visibility = View.VISIBLE
                    errorMessageName.text = context.getString(R.string.error_invalid_char, value[0])
                } else {
                    errorMessageName.visibility = View.INVISIBLE
                }
            }

        }
    }

}