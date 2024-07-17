package com.example.login.error

import android.content.Context
import android.text.Editable
import android.widget.EditText
import android.widget.TextView

class UpdateErrorLoginBuilder(
    val editText: EditText,
    val errorTextView: TextView,
    val context: Context,
    val characterValid: Boolean? = null,
    val securityValid: Boolean? = null

) {
    class Builder {
        private lateinit var editText: EditText
        private lateinit var errorTextView: TextView
        private lateinit var context: Context
        private var characterValid: Boolean? = null
        private var securityValid: Boolean? = null



        fun editText(editText: EditText) = apply { this.editText = editText }
        fun errorTextView(errorTextView: TextView) = apply { this.errorTextView = errorTextView }
        fun context(context: Context) = apply { this.context = context }
        fun characterValid(characterValid: Boolean) = apply { this.characterValid = characterValid }
        fun securityValid(securityValid: Boolean) = apply { this.securityValid = securityValid }

        fun build() = UpdateErrorLoginBuilder(editText, errorTextView, context, characterValid, securityValid)
    }
}
