package com.example.registration.view

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.ViewModelValidations

class ViewErrorSymbolsName{

    fun viewErrorSymbolsName(
        viewModelValidations: ViewModelValidations,
        binding: ActivityRegistrationBinding,
        context: Context
    ){
        viewModelValidations.listenerSymbolsName.observe(context as LifecycleOwner) {
            TODO()
        }
    }

}