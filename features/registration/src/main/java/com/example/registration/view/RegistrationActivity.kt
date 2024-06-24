package com.example.registration.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.registration.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.entity.EntityRegistrations
import com.example.registration.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPhoneNumberEditText()
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
                    updateErrorUI(
                        editText = editName,
                        errorTextView = errorMessageName,
                        charArray = chars,
                        editable = editable
                    )
                }
            )

            editSurname.addTextChangedListenerWithValidation(
                validator = { registrationViewModel.firstNameValidation(it) },
                onError = { chars, editable ->
                    updateErrorUI(
                        editText = editSurname,
                        errorTextView = errorMessageSurname,
                        charArray = chars,
                        editable = editable
                    )
                }
            )

            cancelNameEntry.setOnClickListener { editName.text.clear() }
            cancelSurnameEntry.setOnClickListener { editSurname.text.clear() }
            cancelPhoneNumberEntry.setOnClickListener { editPhoneNumber.text.clear() }
        }
    }

    private fun setupPhoneNumberEditText() {
        with(binding) {
            editPhoneNumber.addTextChangedListener(phoneNumberWatcher)
            editPhoneNumber.setOnFocusChangeListener { _, hasFocus ->
                editPhoneNumber.hint =
                    if (hasFocus) entityRegistrations.phoneNumberEntryPattern else getString(R.string.PhoneNumber)
            }
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
                loginButton.loginButton(
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


    private fun updateErrorUI(
        editText: EditText,
        errorTextView: TextView,
        charArray: List<Char>,
        editable: Editable
    ) {
        if (charArray.isNotEmpty()) {
            editText.setTextColor(Color.RED)
            editText.text.setSpan(UnderlineSpan(), 0, editable.length, 0)
            errorTextView.text = getString(R.string.error_invalid_char, charArray[0])
            errorTextView.visibility = View.VISIBLE
        } else {
            editText.setTextColor(Color.BLACK)
            errorTextView.visibility = View.INVISIBLE
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
                    loginButton.messageErrorPhoneNumber(false, binding, this@RegistrationActivity)
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
            loginButton.loginButton(
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
