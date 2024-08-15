package com.example.registration.activity.view.error

import android.content.Context
import android.text.Editable
import android.widget.EditText
import android.widget.TextView

class UpdateErrorBuilder(
    val editText: EditText,
    val errorTextView: TextView,
    val editable: Editable? = null,
    val context: Context,
    val check: Boolean? = null,
    val charArray: List<Char>? = null,
    val characterValid: Boolean? = null,
    val securityValid: Boolean? = null

) {
    class Builder {

        private lateinit var editText: EditText
        private lateinit var errorTextView: TextView
        private var editable: Editable? = null
        private lateinit var context: Context
        private var check: Boolean? = null
        private var charArray: List<Char>? = null
        private var characterValid: Boolean? = null
        private var securityValid: Boolean? = null



        fun editText(editText: EditText) = apply { this.editText = editText }
        fun errorTextView(errorTextView: TextView) = apply { this.errorTextView = errorTextView }
        fun editable(editable: Editable) = apply { this.editable = editable }
        fun context(context: Context) = apply { this.context = context }
        fun check(check: Boolean) = apply { this.check = check }
        fun charArray(charArray: List<Char>) = apply { this.charArray = charArray }
        fun characterValid(characterValid: Boolean) = apply { this.characterValid = characterValid }
        fun securityValid(securityValid: Boolean) = apply { this.securityValid = securityValid }

        fun build() = UpdateErrorBuilder(editText, errorTextView, editable, context, check, charArray, characterValid, securityValid)
    }
}
