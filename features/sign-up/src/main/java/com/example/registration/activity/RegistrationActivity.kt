package com.example.registration.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.view.ViewErrorSymbolsName
import com.example.registration.viewmodel.ViewModelValidations
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val viewModelValidations: ViewModelValidations by viewModels()
    private val viewErrorSymbolsName: ViewErrorSymbolsName = ViewErrorSymbolsName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editName.addListenerTextName(
            lifecycleScope
        )

        viewErrorSymbolsName.viewErrorSymbolsName(viewModelValidations, binding, this)

    }


    private fun EditText.addListenerTextName(
        coroutineScope: CoroutineScope
    ) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                removeTextChangedListener(this)
                coroutineScope.launch {
                    viewModelValidations.validationName(s.toString())
                }
                addTextChangedListener(this)
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }




}



