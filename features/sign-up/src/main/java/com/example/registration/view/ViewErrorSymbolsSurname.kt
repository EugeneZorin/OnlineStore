package com.example.registration.view

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.ViewModelValidations

class ViewErrorSymbolsSurname {

    fun viewErrorSymbolsSurname (
        viewModelValidations: ViewModelValidations,
        binding: ActivityRegistrationBinding,
        context: Context
    ){
        viewModelValidations.listenerSymbolsName.observe(context as LifecycleOwner) {
            TODO()
        }
    }
}