package com.example.registration.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.registration.R
import com.example.registration.activity.view.FormatPhoneNumber
import com.example.registration.activity.view.SetupPhoneNumberEditText
import com.example.registration.activity.view.UpdateErrorUI
import com.example.registration.activity.view.ViewErrorUI
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val registrationViewModel: RegistrationViewModel by viewModels()

    private val viewErrorUI: ViewErrorUI = ViewErrorUI()
    private var formatPhoneNumber: FormatPhoneNumber = FormatPhoneNumber()
    private val setupPhoneNumberEditText: SetupPhoneNumberEditText = SetupPhoneNumberEditText()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupPhoneNumberEditText.setupPhoneNumberEditText(binding = binding, context = this)
        button()
        setupView()
    }

    // Initialization of input field observers
    private fun setupView() {
        with(binding) {
            editName.addTextChangedListenerWithValidation(
                validatorSymbols = { registrationViewModel.nameValidationSymbols(it) },
                validatorLengths = { registrationViewModel.validationLengths(it.length) },
                onError = { chars, editable, check ->
                    val update = UpdateErrorUI.Builder()
                        .editText(editName)
                        .errorTextView(errorMessageName)
                        .charArray(chars)
                        .editable(editable).context(this@RegistrationActivity)
                        .check(check)
                    viewErrorUI.updateErrorUI(update)
                }
            )

            editSurname.addTextChangedListenerWithValidation(
                validatorSymbols = { registrationViewModel.surnameValidation(it) },
                validatorLengths = { registrationViewModel.validationLengths(it.length) },
                onError = { chars, editable, check ->
                    val update = UpdateErrorUI.Builder()
                        .editText(editSurname)
                        .errorTextView(errorMessageSurname)
                        .charArray(chars)
                        .editable(editable).context(this@RegistrationActivity)
                        .check(check)
                    viewErrorUI.updateErrorUI(update)
                }
            )

            password.addPasswordChangedListenerWithValidation(
                coroutineScope = lifecycleScope,
                validator = { registrationViewModel.passwordValidation(it) },
                onError = { status ->
                    viewErrorUI.updateErrorPassword(
                        password,
                        errorPassword,
                        this@RegistrationActivity,
                        status
                    )

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
        validator: suspend (String) -> Boolean,
        onError: (Boolean) -> Unit
    ) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                coroutineScope.launch {
                    val result = validator(s.toString())
                    onError(result)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun EditText.addTextChangedListenerWithValidation(
        validatorSymbols: (String) -> List<Char>,
        validatorLengths: (String) -> Boolean,
        onError: (List<Char>, Editable, Boolean) -> Unit
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
                onError(charArray, editable, intValue)
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

            private var isFormatting: Boolean = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                validator(s.toString())

                with(binding) {

                    if (isFormatting) {
                        isFormatting = false
                        return
                    }

                    val formattedPhone = formatPhoneNumber.formatPhoneNumber(s)
                    isFormatting = true
                    editPhoneNumber.setText(formattedPhone.toString())
                    editPhoneNumber.setSelection(formattedPhone.length)

                }

            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }


    // Tracks the state of fields and its values,
    // if all data in the registration field is correct,
    // the registration button becomes active.
    private fun button() {

        registrationViewModel.listener.observe(this) { result ->

            when (!result.contains(false)) {
                true -> {
                    binding.button.setBackgroundColor(ContextCompat.getColor(this, R.color.pink))
                    binding.button.setOnClickListener {
                        registrationViewModel.savingData()
                    }
                }

                false -> {
                    binding.button.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.pale_pink
                        )
                    )
                }
            }
        }
    }
}


