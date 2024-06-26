package com.example.registration.activity.view

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.registration.R

class UpdateErrorUI {

    data class Builder(
        var editText: EditText? = null,
        var errorTextView: TextView? = null,
        var charArray: List<Char>? = null,
        var editable: Editable? = null,
        var context: Context? = null,
        var check: Boolean? = null
    ) {
        fun editText(editText: EditText) = apply { this.editText = editText }
        fun errorTextView(errorTextView: TextView) = apply { this.errorTextView = errorTextView }
        fun charArray(charArray: List<Char>) = apply { this.charArray = charArray }
        fun editable(editable: Editable) = apply { this.editable = editable }
        fun context(context: Context) = apply { this.context = context }
        fun check(check: Boolean) = apply { this.check = check }

    }

}
