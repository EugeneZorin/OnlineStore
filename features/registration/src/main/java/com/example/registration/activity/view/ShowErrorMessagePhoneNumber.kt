package com.example.registration.activity.view

import android.content.Context
import android.graphics.Color
import android.text.style.UnderlineSpan
import android.view.View
import com.example.registration.R
import com.example.registration.databinding.ActivityRegistrationBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowErrorMessagePhoneNumber {
    fun showErrorMessagePhoneNumber(
        result: Boolean,
        binding: ActivityRegistrationBinding,
        context: Context
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            with(binding) {
                errorMessageNumberPhone.visibility = if (result) View.VISIBLE else View.INVISIBLE
                editPhoneNumber.setTextColor(if (result) Color.RED else Color.BLACK)
                if (result) {
                    editPhoneNumber.text.setSpan(UnderlineSpan(), 0, editPhoneNumber.text.length, 0)
                    errorMessageNumberPhone.text = context.getString(R.string.error_number_phone)
                }
            }
        }
    }
}