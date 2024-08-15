package com.example.registration.activity.view

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.core.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.RegistrationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhoneNumberVerificationSupervisor {

    // Displays error messages when entering a phone number if an account with that number already exists
    fun phoneNumberVerificationSupervisor(
        registrationViewModel: RegistrationViewModel,
        binding: ActivityRegistrationBinding,
        context: Context
    ) {
        registrationViewModel.listenerNumberPhone.observe(context as LifecycleOwner) { result ->

            if (result && binding.editPhoneNumber.text.length == 17 ) {
                CoroutineScope(Dispatchers.Main).launch {
                    with(binding) {
                        errorMessageNumberPhone.visibility = View.VISIBLE
                        editPhoneNumber.setTextColor(Color.RED)
                        editPhoneNumber.text.setSpan(UnderlineSpan(), 0, editPhoneNumber.text.length, 0)
                        errorMessageNumberPhone.text = context.getString(R.string.error_number_phone)
                    }
                }
            } else {
                // Launch the main page
            }


            binding.editPhoneNumber.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(s?.length!! < 17 ) {
                        binding.errorMessageNumberPhone.visibility = View.INVISIBLE
                        binding.editPhoneNumber.setTextColor(Color.BLACK)
                    }
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }

    }
}