package com.example.registration.presentation

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.registration.R
import com.example.registration.databinding.ActivityRegistrationFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegistrationFragment : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationFragmentBinding
    private val registrationViewModel: RegistrationViewModel by viewModels()

    private var setNumber: Int = 0
    private val sizeNameArray: MutableList<Char> = mutableListOf()
    private val sizeFirsNameArray: MutableList<Char> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityRegistrationFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupPhoneNumberEditText()
        inputFields()
        setupCancelButtons()

    }

    private fun setupPhoneNumberEditText() {
        binding.EditPhoneNumber.addTextChangedListener(phoneNumberWatcher)
        binding.EditPhoneNumber.setOnFocusChangeListener { _, hasFocus ->
            binding.EditPhoneNumber.hint =
                if (hasFocus) "+7 XXX XXX XX XX" else getString(R.string.PhoneNumber)
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

            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun counterArray(charArray: List<Char>, editText: EditText) {
        when (editText.id) {
            R.id.EditName -> {
                sizeNameArray.clear()
                sizeNameArray.addAll(charArray)
            }
            R.id.EditFirstName -> {
                sizeFirsNameArray.clear()
                sizeFirsNameArray.addAll(charArray)
            }
        }

        loginButton()
    }


    private fun loginButton(){

        val validationNumber = setNumber == 17
        val validationName = sizeNameArray.isEmpty()
        val validationFirstName = sizeFirsNameArray.isEmpty()
        val sizeName = binding.EditName.text.isNotEmpty()
        val sizeFirsName = binding.EditFirstName.text.isNotEmpty()

        if (validationNumber && validationName && validationFirstName && sizeName && sizeFirsName){
            binding.button.setBackgroundColor(Color.RED)
        } else {
            binding.button.setBackgroundColor(Color.BLACK)
        }

        binding.button.setOnClickListener {

        }
    }


    private fun inputFields() {
        binding.EditName.addTextChangedListenerWithValidation(
            validator = { registrationViewModel.nameValidation(it) },
            onError = { chars, editable ->
                updateErrorUI(binding.EditName, binding.errorMessageName, chars, editable)
            }
        )

        binding.EditFirstName.addTextChangedListenerWithValidation(
            validator = { registrationViewModel.firstNameValidation(it) },
            onError = { chars, editable ->
                updateErrorUI(binding.EditFirstName, binding.errorMessageSurname, chars, editable)
            }
        )

    }

    private fun setupCancelButtons() {

        binding.cancelNameEntry.setOnClickListener { binding.EditName.text.clear() }
        binding.cancelFirstNameEntry.setOnClickListener { binding.EditFirstName.text.clear() }
        binding.cancelPhoneNumberEntry.setOnClickListener { binding.EditPhoneNumber.text.clear() }

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

            if (isFormatting) {
                isFormatting = false
                return
            }

            val phone = s.toString()
            val formattedPhone = StringBuilder()

            val digitsOnly = phone.replace("\\D".toRegex(), "")

            for (i in digitsOnly.indices) {
                when (i) {
                    0 -> {
                        if (digitsOnly[i] != '7') {
                            formattedPhone.append("+ 7 ")
                        } else {
                            formattedPhone.append("+ ")
                        }
                    }

                    1, 4, 7, 9, 12 -> formattedPhone.append(" ")
                }
                formattedPhone.append(digitsOnly[i])
            }

            isFormatting = true
            binding.EditPhoneNumber.setText(formattedPhone.toString())
            binding.EditPhoneNumber.setSelection(formattedPhone.length)
            setNumber = binding.EditPhoneNumber.text.length

        }

        override fun afterTextChanged(s: Editable?) {}
    }
}