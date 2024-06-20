package com.example.registration.presentation.view

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
import com.example.registration.presentation.entity.EntityRegistrations
import com.example.registration.presentation.viewmodel.RegistrationViewModel
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
    private val sizeFirsNameArray: MutableList<Char> = mutableListOf()
    private val entityRegistrations = EntityRegistrations()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPhoneNumberEditText()
        setupView()

    }


    private fun setupView() {
        with(binding) {

            editName.addTextChangedListenerWithValidation(
                validator = { registrationViewModel.nameValidation(it) },
                onError = { chars, editable ->
                    updateErrorUI(binding.editName, binding.errorMessageName, chars, editable)
                }
            )

            editSurname.addTextChangedListenerWithValidation(
                validator = { registrationViewModel.firstNameValidation(it) },
                onError = { chars, editable ->
                    updateErrorUI(
                        binding.editSurname,
                        binding.errorMessageSurname,
                        chars,
                        editable
                    )
                }
            )

            cancelNameEntry.setOnClickListener { binding.editName.text.clear() }
            cancelSurnameEntry.setOnClickListener { binding.editSurname.text.clear() }
            cancelPhoneNumberEntry.setOnClickListener { binding.editPhoneNumber.text.clear() }

        }
    }


    private fun setupPhoneNumberEditText() {
        binding.editPhoneNumber.addTextChangedListener(phoneNumberWatcher)
        binding.editPhoneNumber.setOnFocusChangeListener { _, hasFocus ->
            binding.editPhoneNumber.hint =
                if (hasFocus) entityRegistrations.phoneNumberEntryPattern else getString(R.string.PhoneNumber)
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

                loginButton()
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
                sizeFirsNameArray.clear()
                sizeFirsNameArray.addAll(charArray)
            }
        }

    }


    private fun loginButton() {

        with(binding){
            val sizeName = editName.text.isNotEmpty()
            val sizeSurname = editSurname.text.isNotEmpty()
            val sizePassword = password.text.isNotEmpty()

            if (phoneNumberLength == 17 && sizeNameArray.isEmpty() && sizeFirsNameArray.isEmpty() && sizeName && sizeSurname ) {
                button.setBackgroundColor(ContextCompat.getColor(this@RegistrationActivity, R.color.pink))
                button.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        val result = registrationViewModel.numberPhoneValidation(editPhoneNumber.text)
                        createAccount(result)
                        messageErrorPhoneNumber(result)
                    }
                }
            } else {
                button.setBackgroundColor(ContextCompat.getColor(this@RegistrationActivity, R.color.pale_pink))
            }
        }
    }

    private fun messageErrorPhoneNumber(result: Boolean){
        CoroutineScope(Dispatchers.Main).launch {
            with(binding){
                errorMessageNumberPhone.visibility = if (result) View.VISIBLE else View.INVISIBLE
                editPhoneNumber.setTextColor(if (result) Color.RED else Color.BLACK)
                if (result) {
                    editPhoneNumber.text.setSpan(UnderlineSpan(), 0, editPhoneNumber.text.length, 0)
                    errorMessageNumberPhone.text = getString(R.string.error_number_phone)
                }
            }
        }
    }

    private fun createAccount(result: Boolean){
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

    private val phoneNumberWatcher = object: TextWatcher {

        private var isFormatting: Boolean = false

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (isFormatting) {
                isFormatting = false
                return
            }

            val formattedPhone = registrationViewModel.formatPhoneNumber(s)

            isFormatting = true

            binding.editPhoneNumber.setText(formattedPhone.toString())
            binding.editPhoneNumber.setSelection(formattedPhone.length)
            phoneNumberLength = binding.editPhoneNumber.text.length

            if (phoneNumberLength < 17) { messageErrorPhoneNumber(false) }
        }

        override fun afterTextChanged(s: Editable?) {
            loginButton()
        }

    }

}