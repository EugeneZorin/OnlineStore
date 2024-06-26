package com.example.registration.activity.view

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.registration.R

class ViewErrorUI {

    // Displays error messages in input fields
    fun updateErrorUI(
        update: UpdateErrorUI.Builder
    ) {
        if (update.charArray?.isNotEmpty() == true) {
            setErrorState(
                update.editText!!,
                update.errorTextView!!,
                update.charArray!!,
                update.editable!!,
                update.context!!,
                update.check!!
            )
        } else {
            setNormalState(update.editText!!, update.errorTextView!!)
        }
    }

    // For first and last name
    private fun setErrorState(
        editText: EditText,
        errorTextView: TextView,
        charArray: List<Char>,
        editable: Editable,
        context: Context,
        check: Boolean
    ) {
        editText.setTextColor(Color.RED)
        editText.text.setSpan(UnderlineSpan(), 0, editable.length, 0)
        errorTextView.text = context.getString(R.string.error_invalid_char, charArray[0])
        errorTextView.visibility = View.VISIBLE

    }

    private fun setNormalState(editText: EditText, errorTextView: TextView) {
        editText.setTextColor(Color.BLACK)
        errorTextView.visibility = View.INVISIBLE
    }


    // For password
    fun updateErrorPassword(
        editText: EditText,
        errorTextView: TextView,
        context: Context,
        status: Boolean
    ){
        if (!status) {
            editText.setTextColor(Color.RED)
            editText.text.setSpan(UnderlineSpan(), 0, editText.length(), 0)
            errorTextView.text = context.getText(R.string.error_password)
            errorTextView.visibility = View.VISIBLE
        } else {
            editText.setTextColor(Color.BLACK)
            errorTextView.visibility = View.INVISIBLE
        }
    }
}