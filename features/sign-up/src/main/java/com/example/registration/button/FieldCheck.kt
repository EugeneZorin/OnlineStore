package com.example.registration.button

import android.content.Context
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.core.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.entity.RegistrationResultEntity
import com.example.registration.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class FieldCheck @Inject constructor(): FieldCheckContract {

    override fun fieldCheck(
        viewModel: ViewModel,
        binding: ActivityRegistrationBinding,
        context: Context
    ){
        val contextObserver = context as LifecycleOwner
        val registrationResultEntity = RegistrationResultEntity()

        with(binding) {
            viewModel.listenerFieldCheck.observe(contextObserver) { states ->
                if (states) {
                    singUpButton.setBackgroundColor(ContextCompat.getColor(context, R.color.pink))
                    singUpButton.setOnClickListener {
                        viewModel.createAccount()
                        viewModel.resultRegistration.observe(contextObserver) { result ->
                            when (result) {
                                registrationResultEntity.createAccount -> {
                                    // TODO
                                }
                                registrationResultEntity.errorCreateUser -> {
                                    viewModel.listenerFieldCheck.value = false
                                    errorMessageNumberPhone.visibility = View.VISIBLE
                                    errorMessageNumberPhone.text = context.getString(R.string.error_number_phone)
                                }
                            }
                        }
                    }
                } else {
                    singUpButton.setBackgroundColor(ContextCompat.getColor(context, R.color.pale_pink))
                    errorMessageNumberPhone.visibility = View.INVISIBLE
                }
            }
        }

    }


}

