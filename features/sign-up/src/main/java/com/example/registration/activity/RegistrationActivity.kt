package com.example.registration.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.registration.button.FieldCheckContract
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.error.ErrorNameSurnameView
import com.example.registration.error.contract.ErrorPassword
import com.example.registration.viewmodel.ViewModel
import com.example.registration.watcher.PhoneNumberTextWatcher
import com.example.registration.watcher.ValidationTextWatcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    private val viewModel: ViewModel by viewModels()

    @Inject
    lateinit var errorSymbolsNameSurname: ErrorNameSurnameView

    @Inject
    lateinit var errorPassword: ErrorPassword

    @Inject
    lateinit var fieldCheck: FieldCheckContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()

        fieldCheck.fieldCheck( viewModel, binding ,this)
        errorSymbolsNameSurname.errorNameSurnameHolder(viewModel, binding, this)
        errorPassword.errorPasswordHolder(viewModel, binding, this)

    }

    private fun setup() {

        with(binding) {
            editName.addTextChangedListener(
                ValidationTextWatcher { viewModel.validationName(it) }
            )

            editSurname.addTextChangedListener(
                ValidationTextWatcher { viewModel.validationSurname(it) }
            )

            editPhoneNumber.addTextChangedListener(
                PhoneNumberTextWatcher(
                    setFormatNumberPhone = { viewModel.setFormatNumberPhone(it) },
                    viewNumberPhone = editPhoneNumber,
                    editNumberPhone = editPhoneNumber
                )
            )

            editPassword.addTextChangedListener(
                ValidationTextWatcher {
                    viewModel.validationPasswordSecurity(it)
                }
            )
            editPassword.addTextChangedListener(
                ValidationTextWatcher {
                    viewModel.validationPasswordCharacter(it)
                }
            )
        }
    }
}



