package com.example.login.error

import android.graphics.Color
import android.text.style.UnderlineSpan
import android.util.Log
import com.example.core.R
import android.view.View


class ViewErrorPassword {

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