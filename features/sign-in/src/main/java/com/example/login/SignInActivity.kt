package com.example.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login.databinding.ActivitySignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
    }


    fun setView(){
        binding.editPhoneNumber.addListenerPhoneNumber()
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
}