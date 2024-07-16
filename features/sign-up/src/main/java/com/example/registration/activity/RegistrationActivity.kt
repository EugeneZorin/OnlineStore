package com.example.registration.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.registration.R
import com.example.registration.activity.view.PhoneNumberVerificationSupervisor
import com.example.registration.activity.view.SetupPhoneNumberEditText
import com.example.registration.activity.view.error.UpdateErrorBuilder
import com.example.registration.activity.view.error.ViewErrorUI
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val registrationViewModel: RegistrationViewModel by viewModels()
    private val buttonRegistration: ButtonRegistration = ButtonRegistration()
    private val phoneNumberVerificationSupervisor: PhoneNumberVerificationSupervisor =
        PhoneNumberVerificationSupervisor()

    private val viewErrorUI: ViewErrorUI by lazy { ViewErrorUI() }
    private val setupPhoneNumberEditText: SetupPhoneNumberEditText = SetupPhoneNumberEditText()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPhoneNumberEditText.setupPhoneNumberEditText(binding = binding, context = this)
        buttonRegistration.buttonRegistration(registrationViewModel, binding, this)
        phoneNumberVerificationSupervisor.phoneNumberVerificationSupervisor(
            registrationViewModel,
            binding,
            this
        )

        setupView()
    }

    // Initialization of input field observers
    private fun setupView() {
        with(binding) {
            editName.addTextChangedListenerWithValidation(
                validatorSymbols = { registrationViewModel.nameValidationSymbols(it) },
                validatorLengths = { registrationViewModel.validationNameLengths(it.length) },
                onErrorSymbols = { chars, editable, check ->
                    val update = UpdateErrorBuilder.Builder()
                        .editText(editName)
                        .errorTextView(errorMessageName)
                        .charArray(chars)
                        .editable(editable)
                        .context(this@RegistrationActivity)
                        .check(check)
                        .build()
                    viewErrorUI.updateErrorUI(update)
                }

            )

            editSurname.addTextChangedListenerWithValidation(
                validatorSymbols = { registrationViewModel.surnameValidation(it) },
                validatorLengths = { registrationViewModel.validationSurnameLengths(it.length) },
                onErrorSymbols = { chars, editable, check ->
                    val update = UpdateErrorBuilder.Builder()
                        .editText(editSurname)
                        .errorTextView(errorMessageSurname)
                        .charArray(chars)
                        .editable(editable)
                        .context(this@RegistrationActivity)
                        .check(check)
                        .build()
                    viewErrorUI.updateErrorUI(update)
                }
            )

            password.addPasswordChangedListenerWithValidation(
                coroutineScope = lifecycleScope,
                validatorCharacter = { registrationViewModel.passwordValidationCharacter(it) },
                validatorSecurity = { registrationViewModel.passwordValidationSecurity(it) },
                onError = { characterValid, securityValid ->
                    val update = UpdateErrorBuilder.Builder()
                        .editText(password)
                        .errorTextView(errorPassword)
                        .context(this@RegistrationActivity)
                        .characterValid(characterValid)
                        .securityValid(securityValid)
                        .build()
                    viewErrorUI.passwordErrorHandler(update)


                }
            )

            editPhoneNumber.addPhoneNumberChangedListenerWithValidation(
                validator = { registrationViewModel.validationLengthNumberPhone(it) }
            )

            cancelNameEntry.setOnClickListener { editName.text.clear() }
            cancelSurnameEntry.setOnClickListener { editSurname.text.clear() }
            cancelPhoneNumberEntry.setOnClickListener { editPhoneNumber.text.clear() }
            cancelPassword.setOnClickListener { password.text.clear() }
        }
    }


    private fun EditText.addPasswordChangedListenerWithValidation(
        coroutineScope: CoroutineScope,
        validatorCharacter: suspend (String) -> Boolean,
        validatorSecurity: suspend (String) -> Boolean,
        onError: (Boolean, Boolean) -> Unit,

        ) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                coroutineScope.launch {
                    val resultCharacter = validatorCharacter(s.toString())
                    val resultSecurity = validatorSecurity(s.toString())
                    onError(resultCharacter, resultSecurity)

                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun EditText.addTextChangedListenerWithValidation(
        validatorSymbols: (String) -> List<Char>,
        validatorLengths: (String) -> Boolean,
        onErrorSymbols: (List<Char>, Editable, Boolean) -> Unit,
    ) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val editable = this@addTextChangedListenerWithValidation.text ?: return
                val capitalized = capitalizeFirstLetter(s.toString())
                this@addTextChangedListenerWithValidation.removeTextChangedListener(this)
                this@addTextChangedListenerWithValidation.setText(capitalized)
                this@addTextChangedListenerWithValidation.setSelection(capitalized.length)
                val charArray = validatorSymbols(capitalized)
                val intValue = validatorLengths(capitalized)
                this@addTextChangedListenerWithValidation.addTextChangedListener(this)
                onErrorSymbols(charArray, editable, intValue)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun capitalizeFirstLetter(input: String): String {
        return input.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
    }

    private fun EditText.addPhoneNumberChangedListenerWithValidation(
        validator: (String) -> Boolean,
    ) {
        this.addTextChangedListener(object : TextWatcher {


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validator(s.toString())


            }

            override fun afterTextChanged(s: Editable?) {
                removeTextChangedListener(this)
                with(binding) {
                    val formattedPhone = registrationViewModel.setFormatNumber(s)
                    editPhoneNumber.setText(formattedPhone.toString())
                    editPhoneNumber.setSelection(formattedPhone.length)
                }
                addTextChangedListener(this)
            }
        })
    }


}


