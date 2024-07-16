package com.example.login.error

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.style.UnderlineSpan
import com.example.core.R
import android.view.View
import android.widget.EditText
import android.widget.TextView


class ViewErrorUILogin {

    // Updates the error UI specifically for password fields
    fun passwordErrorHandler(
        update: UpdateErrorLoginBuilder
    ){
        with(update) {
            when {
                !characterValid!! && editText.text.isNotEmpty() -> {
                    editText.setTextColor(Color.RED)
                    editText.text.setSpan(UnderlineSpan(), 0, editText.length(), 0)
                    errorTextView.text = context.getText(R.string.error_password_character)
                    errorTextView.visibility = View.VISIBLE
                }
                !securityValid!! && editText.text.isNotEmpty() -> {
                    editText.setTextColor(Color.RED)
                    editText.text.setSpan(UnderlineSpan(), 0, editText.length(), 0)
                    errorTextView.text = context.getText(R.string.error_password_entry)
                    errorTextView.visibility = View.VISIBLE
                }
                else -> {
                    editText.setTextColor(Color.BLACK)
                    errorTextView.visibility = View.INVISIBLE
                }
            }
        }
    }

}