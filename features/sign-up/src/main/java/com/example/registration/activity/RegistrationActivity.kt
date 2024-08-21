package com.example.registration.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.view.error.ViewErrorSymbolsName
import com.example.registration.view.error.ViewErrorSymbolsSurname
import com.example.registration.viewmodel.ViewModelSetFormat
import com.example.registration.viewmodel.ViewModelValidations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val viewModelValidations: ViewModelValidations by viewModels()
    private val viewModelSetFormat: ViewModelSetFormat by viewModels()

    private val viewErrorSymbolsName: ViewErrorSymbolsName = ViewErrorSymbolsName()
    private val viewErrorSymbolsSurname: ViewErrorSymbolsSurname = ViewErrorSymbolsSurname()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setup()

        viewErrorSymbolsName.viewErrorSymbolsName(viewModelValidations, binding, this)
        viewErrorSymbolsSurname.viewErrorSymbolsSurname(viewModelValidations, binding, this)

    }

    private fun setup() {
        with(binding) {
            editName.addListenerTextNameSurname(
                validatorCharacter = { viewModelValidations.validationName(it) }
            )

            editSurname.addListenerTextNameSurname(
                validatorCharacter = { viewModelValidations.validationSurname(it) }
            )

            editPhoneNumber.addListenerNumberPhone(
                setFormatNumberPhone = {viewModelSetFormat.setFormatNumberPhone(it) },
                viewNumberPhone = editPhoneNumber,
                editNumberPhone = editPhoneNumber
            )
        }
    }


    private fun EditText.addListenerTextNameSurname(
        validatorCharacter: (String) -> Unit
    ) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatorCharacter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun EditText.addListenerNumberPhone(
        setFormatNumberPhone: (String) -> CharSequence,
        viewNumberPhone: (TextView),
        editNumberPhone: (EditText),
    ) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                removeTextChangedListener(this)
                val number = setFormatNumberPhone(s.toString())
                viewNumberPhone.text = number
                editNumberPhone.setSelection(number.length)
                addTextChangedListener(this)
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }
}



