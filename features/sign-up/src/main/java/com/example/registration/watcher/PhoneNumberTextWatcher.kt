package com.example.registration.watcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

class PhoneNumberTextWatcher(
    private val setFormatNumberPhone: (String) -> CharSequence,
    private val viewNumberPhone: TextView,
    private val editNumberPhone: EditText
): TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        editNumberPhone.removeTextChangedListener(this)
        val number = setFormatNumberPhone(s.toString())
        viewNumberPhone.text = number
        editNumberPhone.setSelection(number.length)
        editNumberPhone.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {}
}