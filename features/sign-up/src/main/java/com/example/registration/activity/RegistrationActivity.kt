package com.example.registration.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.view.error.contract.ErrorPassword
import com.example.registration.view.error.contract.ErrorSymbolsNameSurname
import com.example.registration.viewmodel.ViewModelSetFormat
import com.example.registration.viewmodel.ViewModelValidations
import com.example.registration.watcher.PhoneNumberTextWatcher
import com.example.registration.watcher.ValidationTextWatcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    private val viewModelValidations: ViewModelValidations by viewModels()
    private val viewModelSetFormat: ViewModelSetFormat by viewModels()

    @Inject
    lateinit var errorSymbolsNameSurname: ErrorSymbolsNameSurname

    @Inject
    lateinit var errorPassword: ErrorPassword


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()

        errorSymbolsNameSurname.viewErrorSymbolsName(viewModelValidations, binding, this)
        errorSymbolsNameSurname.viewErrorSymbolsSurname(viewModelValidations, binding, this)
        errorPassword.errorPasswordHolder(viewModelValidations, binding, this)

    }

    private fun setup() {
        with(binding) {
            editName.addTextChangedListener(
                ValidationTextWatcher { viewModelValidations.validationName(it) }
            )

            editSurname.addTextChangedListener(
                ValidationTextWatcher { viewModelValidations.validationSurname(it) }
            )

            editPhoneNumber.addTextChangedListener(
                PhoneNumberTextWatcher(
                    setFormatNumberPhone = { viewModelSetFormat.setFormatNumberPhone(it) },
                    viewNumberPhone = editPhoneNumber,
                    editNumberPhone = editPhoneNumber
                )
            )

            editPassword.addTextChangedListener(
                ValidationTextWatcher {
                    viewModelValidations.validationPasswordSecurity(it)
                }
            )
            editPassword.addTextChangedListener(
                ValidationTextWatcher {
                    viewModelValidations.validationPasswordCharacter(it)
                }
            )
        }
    }

}



