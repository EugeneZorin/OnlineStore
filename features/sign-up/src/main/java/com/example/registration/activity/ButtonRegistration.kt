package com.example.registration.activity

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.core.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.RegistrationViewModel

class ButtonRegistration {

    // Tracks the state of fields and its values,
    // if all data in the registration field is correct,
    // the registration button becomes active.
    fun buttonRegistration(
        registrationViewModel: RegistrationViewModel,
        binding:ActivityRegistrationBinding,
        context: Context
    ) {
        with(binding) {
            registrationViewModel.listener.observe(context as LifecycleOwner) { result ->

                when (!result.contains(false)) {
                    true -> {
                        button.setBackgroundColor(ContextCompat.getColor(context, R.color.pink))
                        button.setOnClickListener {
                            registrationViewModel.savingData()
                        }
                    }

                    false -> {
                        button.setBackgroundColor(
                            ContextCompat.getColor(
                                context,
                                R.color.pale_pink
                            )
                        )
                    }
                }
            }
        }

    }
}