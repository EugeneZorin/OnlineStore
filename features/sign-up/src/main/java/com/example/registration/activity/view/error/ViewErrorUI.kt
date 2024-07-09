package com.example.registration.activity.view.error

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.registration.R

class ViewErrorUI {

    // Updates the error UI for input fields based on given conditions
    fun updateErrorUI(update: UpdateErrorBuilder) {
        when {
            update.charArray?.isNotEmpty() == true -> {
                setErrorStateSymbols(
                    update.editText,
                    update.errorTextView,
                    update.charArray,
                    update.editable!!,
                    update.context
                )
            }
            update.check == false && update.editText.text.isNotEmpty() -> {
                setErrorStateLengths(
                    update.editText,
                    update.errorTextView,
                    update.editable!!,
                    update.context
                )
            }
            else -> {
                setNormalState(update.editText, update.errorTextView)
            }
        }
    }

    // Sets the error state for length-related errors
    private fun setErrorStateLengths(
        editText: EditText,
        errorTextView: TextView,
        editable: Editable,
        context: Context
    ) {
        editText.setTextColor(Color.RED)
        editText.text.setSpan(UnderlineSpan(), 0, editable.length, 0)
        errorTextView.text = context.getString(R.string.error_lengths)
        errorTextView.visibility = View.VISIBLE
    }

    // Sets the error state for symbol-related errors
    private fun setErrorStateSymbols(
        editText: EditText,
        errorTextView: TextView,
        charArray: List<Char>,
        editable: Editable,
        context: Context
    ) {
        editText.setTextColor(Color.RED)
        editText.text.setSpan(UnderlineSpan(), 0, editable.length, 0)
        errorTextView.text = context.getString(R.string.error_invalid_char, charArray[0])
        errorTextView.visibility = View.VISIBLE
    }

    // Resets the UI to normal state
    private fun setNormalState(editText: EditText, errorTextView: TextView) {
        editText.setTextColor(Color.BLACK)
        errorTextView.visibility = View.INVISIBLE
    }

    // Updates the error UI specifically for password fields
    fun passwordErrorHandler(
        update: UpdateErrorBuilder
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
                    errorTextView.text = context.getText(R.string.error_password_security)
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