package com.example.registration.watcher

import android.text.Editable
import android.text.TextWatcher

class ValidationTextWatcher(
    private val validationSymbol: (String) -> Unit
): TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        validationSymbol(s.toString())
    }

    override fun afterTextChanged(s: Editable?) {}
}