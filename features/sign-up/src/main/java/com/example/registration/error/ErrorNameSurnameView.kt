package com.example.registration.error

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.core.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.error.contract.ErrorNameSurname
import com.example.registration.viewmodel.ViewModel
import javax.inject.Inject

class ErrorNameSurnameView @Inject constructor() : ErrorNameSurname {

    override fun errorNameSurnameHolder(
        viewModel: ViewModel,
        binding: ActivityRegistrationBinding,
        context: Context
    ) {

        viewModel.listenerSymbolsName.observe(context as LifecycleOwner) { symbolValid ->
            val lengthsNameValid = viewModel.lengthsName.value ?: false
            updateNameError(binding, context, symbolValid, lengthsNameValid)
        }

        viewModel.lengthsName.observe(context as LifecycleOwner) { lengthsValid ->
            val symbolNameValid = viewModel.listenerSymbolsName.value
            updateNameError(binding, context, symbolNameValid, lengthsValid)
        }

        viewModel.listenerSymbolsSurname.observe(context as LifecycleOwner) { symbolValid ->
            val lengthsNameValid = viewModel.lengthsSurname.value ?: false
            updateSurnameError(binding, context, symbolValid, lengthsNameValid)
        }

        viewModel.lengthsSurname.observe(context as LifecycleOwner) { lengthsValid ->
            val symbolNameValid = viewModel.listenerSymbolsSurname.value
            updateSurnameError(binding, context, symbolNameValid, lengthsValid)
        }
    }

    private fun updateSurnameError(
        binding: ActivityRegistrationBinding,
        context: Context,
        symbolSurnameValid: List<Char>?,
        lengthsSurnameValid: Boolean,
    ) {
        with(binding.errorMessageSurname) {
            if (!symbolSurnameValid.isNullOrEmpty()) {
                visibility = View.VISIBLE
                text = context.getString(R.string.error_invalid_char, symbolSurnameValid[0])
            } else if (!lengthsSurnameValid) {
                visibility = View.VISIBLE
                text = context.getString(R.string.error_lengths)
            } else {
                visibility = View.INVISIBLE
            }
        }
    }


    private fun updateNameError(
        binding: ActivityRegistrationBinding,
        context: Context,
        symbolNameValid: List<Char>?,
        lengthsNameValid: Boolean,
    ) {
        with(binding.errorMessageName) {
           if (!symbolNameValid.isNullOrEmpty()) {
               visibility = View.VISIBLE
               text = context.getString(R.string.error_invalid_char, symbolNameValid[0])
           } else if (!lengthsNameValid) {
               visibility = View.VISIBLE
               text = context.getString(R.string.error_lengths)
           } else {
               visibility = View.INVISIBLE
           }
        }
    }
}