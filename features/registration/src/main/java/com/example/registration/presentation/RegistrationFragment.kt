package com.example.registration.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.registration.R
import com.example.registration.databinding.ActivityRegistrationFragmentBinding
import com.example.registration.repository.saving.DataSavingContract
import com.example.registration.repository.validation.DataValidationContract
import com.example.registration.repository.validation.NumberPhoneValidationContract
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class RegistrationFragment : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationFragmentBinding

    @Inject lateinit var numberPhoneValidationContract: NumberPhoneValidationContract
    @Inject lateinit var dataSavingContract: DataSavingContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPhoneNumberEditText()
        setupCancelButtons()

    }




    private fun setupPhoneNumberEditText() {
        binding.EditPhoneNumber.addTextChangedListener(phoneNumberWatcher)
        binding.EditPhoneNumber.setOnFocusChangeListener { _, hasFocus ->
            binding.EditPhoneNumber.hint = if (hasFocus) "+7 XXX XXX XX XX" else getString(R.string.PhoneNumber)
        }
    }

    private fun setupCancelButtons() {
        binding.EditName.addTextChangedListener {
            toggleCancelButton(binding.cancelNameEntry, it)
        }
        binding.EditFirstName.addTextChangedListener { toggleCancelButton(binding.cancelFirstNameEntry, it) }
        binding.EditPhoneNumber.addTextChangedListener { toggleCancelButton(binding.cancelPhoneNumberEntry, it) }

        binding.cancelNameEntry.setOnClickListener { binding.EditName.text.clear() }
        binding.cancelFirstNameEntry.setOnClickListener { binding.EditFirstName.text.clear() }
        binding.cancelPhoneNumberEntry.setOnClickListener { binding.EditPhoneNumber.text.clear() }
    }

    private fun toggleCancelButton(button: View, text: CharSequence?) {
        button.alpha = if (text.isNullOrEmpty()) 0.0f else 1.0f
        CoroutineScope(Dispatchers.IO).launch {
            numberPhoneValidationContract.validationNumberPhone(button.toString())
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


        }

        override fun afterTextChanged(s: Editable?) {}
    }
}