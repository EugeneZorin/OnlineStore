package com.example.registration.activity.view

import android.content.Context
import android.text.TextWatcher
import com.example.registration.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.entity.EntityRegistrations

class SetupPhoneNumberEditText {

    private val entityRegistrations = EntityRegistrations()

    fun setupPhoneNumberEditText(
        binding: ActivityRegistrationBinding,
        context: Context
    ) {
        with(binding) {
            editPhoneNumber.setOnFocusChangeListener { _, hasFocus ->
                editPhoneNumber.hint =
                    if (hasFocus) entityRegistrations.phoneNumberEntryPattern else context.getString(
                        R.string.PhoneNumber
                    )
            }
        }
    }
}