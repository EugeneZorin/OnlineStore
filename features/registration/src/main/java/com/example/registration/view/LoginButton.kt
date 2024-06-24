package com.example.registration.view

import android.content.Context
import android.graphics.Color
import android.text.style.UnderlineSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.example.registration.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.entity.EntityRegistrations
import com.example.registration.viewmodel.RegistrationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginButton {

    private val entityRegistrations = EntityRegistrations()


    fun handleLoginButton(
        binding: ActivityRegistrationBinding,
        phoneNumberLength: Int,
        sizeNameArray: MutableList<Char>,
        sizeFirstNameArray: MutableList<Char>,
        context: Context,
        registrationViewModel: RegistrationViewModel
    ) {
        with(binding) {
            val sizeName = editName.text.isNotEmpty()
            val sizeSurname = editSurname.text.isNotEmpty()

            if (isLoginButtonEnabled(phoneNumberLength, sizeNameArray, sizeFirstNameArray, sizeName, sizeSurname)) {
                enableLoginButton(binding, context, registrationViewModel)
            } else {
                disableLoginButton(binding, context)
            }
        }
    }

    private fun isLoginButtonEnabled(
        phoneNumberLength: Int,
        sizeNameArray: MutableList<Char>,
        sizeFirstNameArray: MutableList<Char>,
        sizeName: Boolean,
        sizeSurname: Boolean
    ): Boolean {
        return phoneNumberLength == entityRegistrations.seventeen && sizeNameArray.isEmpty() && sizeFirstNameArray.isEmpty() && sizeName && sizeSurname
    }

    private fun enableLoginButton(
        binding: ActivityRegistrationBinding,
        context: Context,
        registrationViewModel: RegistrationViewModel
    ) {
        with(binding) {
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.pink))
            button.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val result = registrationViewModel.numberPhoneValidation(editPhoneNumber.text)
                    createAccount(result, binding, registrationViewModel)
                    showErrorMessagePhoneNumber(result, binding, context)
                }
            }
        }
    }

    private fun disableLoginButton(binding: ActivityRegistrationBinding, context: Context) {
        with(binding) {
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.pale_pink))
        }
    }

    private fun createAccount(result: Boolean, binding: ActivityRegistrationBinding, registrationViewModel: RegistrationViewModel) {
        if (!result) {
            CoroutineScope(Dispatchers.Main).launch {
                with(binding) {
                    registrationViewModel.savingData(
                        editPhoneNumber.text.toString(),
                        password.text.toString(),
                        editName.text.toString(),
                        editSurname.text.toString()
                    )
                }
            }
        }
    }

    fun showErrorMessagePhoneNumber(result: Boolean, binding: ActivityRegistrationBinding, context: Context) {
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