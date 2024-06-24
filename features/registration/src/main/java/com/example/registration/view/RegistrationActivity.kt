package com.example.registration.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.registration.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.entity.EntityRegistrations
import com.example.registration.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val registrationViewModel: RegistrationViewModel by viewModels()

    private var phoneNumberLength: Int = 0
    private val sizeNameArray: MutableList<Char> = mutableListOf()
    private val sizeFirstNameArray: MutableList<Char> = mutableListOf()
    private val entityRegistrations = EntityRegistrations()

    private var formatPhoneNumber: FormatPhoneNumber = FormatPhoneNumber()
    private val loginButton: LoginButton = LoginButton()
    private val updateErrorUI: UpdateErrorUI = UpdateErrorUI()
    private val setupPhoneNumberEditText: SetupPhoneNumberEditText = SetupPhoneNumberEditText()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPhoneNumberEditText.setupPhoneNumberEditText(
            binding = binding,
            phoneNumberWatcher = phoneNumberWatcher,
            context = this
        )

        setupView()
    }

    override fun onRestart() {
        super.onRestart()
        binding.errorMessageSurname.text = getString(R.string.PhoneNumber)
    }

    private fun setupView() {
        with(binding) {
            editName.addTextChangedListenerWithValidation(
                validator = { registrationViewModel.nameValidation(it) },
                onError = { chars, editable ->
                    updateErrorUI.updateErrorUI(
                        editText = editName,
                        errorTextView = errorMessageName,
                        charArray = chars,
                        editable = editable,
                        context = this@RegistrationActivity
                    )
                }
            )

            editSurname.addTextChangedListenerWithValidation(
                validator = { registrationViewModel.firstNameValidation(it) },
                onError = { chars, editable ->
                    updateErrorUI.updateErrorUI(
                        editText = editSurname,
                        errorTextView = errorMessageSurname,
                        charArray = chars,
                        editable = editable,
                        context = this@RegistrationActivity
                    )
                }
            )

            cancelNameEntry.setOnClickListener { editName.text.clear() }
            cancelSurnameEntry.setOnClickListener { editSurname.text.clear() }
            cancelPhoneNumberEntry.setOnClickListener { editPhoneNumber.text.clear() }
        }
    }


    fun EditText.addTextChangedListenerWithValidation(
        validator: (String) -> List<Char>,
        onError: (List<Char>, Editable) -> Unit
    ) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val editable = this@addTextChangedListenerWithValidation.text ?: return
                val charArray = validator(s.toString())
                onError(charArray, editable)
                counterArray(charArray, this@addTextChangedListenerWithValidation)
                loginButton.handleLoginButton(
                    binding = binding,
                    phoneNumberLength = phoneNumberLength,
                    sizeNameArray = sizeNameArray,
                    sizeFirstNameArray = sizeFirstNameArray,
                    context = this@RegistrationActivity,
                    registrationViewModel = registrationViewModel
                )
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun counterArray(charArray: List<Char>, editText: EditText) {
        when (editText.id) {
            R.id.editName -> {
                sizeNameArray.clear()
                sizeNameArray.addAll(charArray)
            }
            R.id.editSurname -> {
                sizeFirstNameArray.clear()
                sizeFirstNameArray.addAll(charArray)
            }
        }
    }

    private val phoneNumberWatcher = object : TextWatcher {
        private var isFormatting: Boolean = false

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            with(binding) {
                if (isFormatting) {
                    isFormatting = false
                    return
                }

                val formattedPhone = formatPhoneNumber.formatPhoneNumber(s)
                isFormatting = true
                editPhoneNumber.setText(formattedPhone.toString())
                editPhoneNumber.setSelection(formattedPhone.length)
                phoneNumberLength = editPhoneNumber.text.length

                if (phoneNumberLength < entityRegistrations.seventeen) {
                    loginButton.showErrorMessagePhoneNumber(false, binding, this@RegistrationActivity)
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
            loginButton.handleLoginButton(
                binding = binding,
                phoneNumberLength = phoneNumberLength,
                sizeNameArray = sizeNameArray,
                sizeFirstNameArray = sizeFirstNameArray,
                context = this@RegistrationActivity,
                registrationViewModel = registrationViewModel
            )
        }
    }
}
