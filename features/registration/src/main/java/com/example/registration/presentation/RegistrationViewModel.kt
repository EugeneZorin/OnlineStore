package com.example.registration.presentation

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.registration.R
import com.example.registration.repository.saving.DataSaving
import com.example.registration.repository.validation.DataValidation
import com.example.registration.repository.validation.NumberPhoneValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val dataValidation: DataValidation,
    private val numberPhoneValidation: NumberPhoneValidation,
    private val dataSaving: DataSaving
): ViewModel() {

    private val _resultNumberValidation: MutableLiveData<Boolean> = MutableLiveData()
    val resultNumberValidation: MutableLiveData<Boolean>
        get() = _resultNumberValidation


    fun nameValidation(name: String): MutableList<Char> {
        return dataValidation.validationName(name)
    }

    fun firstNameValidation(firstName: String): MutableList<Char> {
        return dataValidation.validationFirstName(firstName)
    }

    suspend fun numberPhoneValidation(number: Editable): Boolean {
        return numberPhoneValidation.validationNumberPhone(number.toString())
    }




}