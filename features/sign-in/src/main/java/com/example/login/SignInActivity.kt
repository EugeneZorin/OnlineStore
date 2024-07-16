package com.example.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.login.databinding.ActivitySignInBinding
import com.example.login.error.UpdateErrorLoginBuilder
import com.example.login.error.ViewErrorUILogin
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()
    private val viewErrorUI: ViewErrorUILogin by lazy { ViewErrorUILogin() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
    }


    private fun setView(){
        with(binding) {
            editPhoneNumber.addListenerPhoneNumber()

            editPassword.addPasswordChangedListenerWithValidation(
                coroutineScope = lifecycleScope,
                validatorCharacter = { signInViewModel.passwordValidationCharacter(it) },
                validatorSecurity = {signInViewModel.passwordValidationSecurity(it)},
                onError = { characterValid, securityValid ->
                    val update = UpdateErrorLoginBuilder.Builder()
                        .editText(editPassword)
                        .errorTextView(errorMessagePassword)
                        .context(this@SignInActivity)
                        .characterValid(characterValid)
                        .securityValid(securityValid)
                        .build()
                    viewErrorUI.passwordErrorHandler(update)
                }
            )
        }

    }


    private fun EditText.addListenerPhoneNumber() {
        this.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                removeTextChangedListener(this)

                val formattedPhoneNumber = signInViewModel.setFormatNumberPhone(s)
                binding.editPhoneNumber.setText(formattedPhoneNumber.toString())
                binding.editPhoneNumber.setSelection(formattedPhoneNumber.length)

                addTextChangedListener(this)
            }

        })
    }

    private fun EditText.addPasswordChangedListenerWithValidation(
        coroutineScope: CoroutineScope,
        validatorCharacter: suspend (String) -> Boolean,
        validatorSecurity: suspend (String) -> Boolean,
        onError: (Boolean, Boolean) -> Unit,

        ) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                coroutineScope.launch {
                    val resultCharacter = validatorCharacter(s.toString())
                    val resultSecurity = validatorSecurity(s.toString())
                    onError(resultCharacter, resultSecurity)

                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}