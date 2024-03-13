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
import androidx.core.content.ContextCompat
import com.example.registration.R
import com.example.registration.databinding.ActivityRegistrationFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RegistrationFragment : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationFragmentBinding
    private val registrationViewModel: RegistrationViewModel by viewModels()

    private var phoneNumberLength: Int = 0
    private val sizeNameArray: MutableList<Char> = mutableListOf()
    private val sizeFirsNameArray: MutableList<Char> = mutableListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPhoneNumberEditText()
        setupView()
    }


    private fun setupView() {
        with(binding) {

            EditName.addTextChangedListenerWithValidation(
                validator = { registrationViewModel.nameValidation(it) },
                onError = { chars, editable ->
                    updateErrorUI(binding.EditName, binding.errorMessageName, chars, editable)
                }
            )

            EditSurname.addTextChangedListenerWithValidation(
                validator = { registrationViewModel.firstNameValidation(it) },
                onError = { chars, editable ->
                    updateErrorUI(
                        binding.EditSurname,
                        binding.errorMessageSurname,
                        chars,
                        editable
                    )
                }
            )

            cancelNameEntry.setOnClickListener { binding.EditName.text.clear() }
            cancelSurnameEntry.setOnClickListener { binding.EditSurname.text.clear() }
            cancelPhoneNumberEntry.setOnClickListener { binding.EditPhoneNumber.text.clear() }

        }
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

                loginButton()
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

    }


    private fun loginButton() {

        with(binding){
            val sizeName = EditName.text.isNotEmpty()
            val sizeSurname = EditSurname.text.isNotEmpty()

            if (phoneNumberLength == 17 && sizeNameArray.isEmpty() && sizeFirsNameArray.isEmpty() && sizeName && sizeSurname) {
                button.setBackgroundColor(ContextCompat.getColor(this@RegistrationFragment, R.color.pink))
                button.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        val result = registrationViewModel.numberPhoneValidation(EditPhoneNumber.text)
                        createAccount(result)
                        messageErrorPhoneNumber(result)
                    }
                }
            } else {
                button.setBackgroundColor(ContextCompat.getColor(this@RegistrationFragment, R.color.pale_pink))
            }
        }
    }

    private fun messageErrorPhoneNumber(result: Boolean){
        CoroutineScope(Dispatchers.Main).launch {
            with(binding){
                errorMessageNumberPhone.visibility = if (result) View.VISIBLE else View.INVISIBLE
                EditPhoneNumber.setTextColor(if (result) Color.RED else Color.BLACK)
                if (result) {
                    EditPhoneNumber.text.setSpan(UnderlineSpan(), 0, EditPhoneNumber.text.length, 0)
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
                        EditName.text.toString(),
                        EditSurname.text.toString(),
                        EditPhoneNumber.text.toString()
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
            phoneNumberLength = binding.EditPhoneNumber.text.length

            if (phoneNumberLength < 17) { messageErrorPhoneNumber(false) }

        }

        override fun afterTextChanged(s: Editable?) {
            loginButton()
        }
    }
}