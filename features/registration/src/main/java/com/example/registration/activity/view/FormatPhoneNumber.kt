package com.example.registration.activity.view

class FormatPhoneNumber {

    fun formatPhoneNumber(input: CharSequence?): CharSequence {

        val formattedPhone = StringBuilder()

        val digitsOnly = input.toString().replace("\\D".toRegex(), "")

        for (i in digitsOnly.indices) {
            when (i) {
                0 -> {
                    if (digitsOnly[i] != '7') {
                        formattedPhone.append("+ 7 ")
                    } else {
                        formattedPhone.append("+ ")
                    }
                }

                1, 4, 7, 9, 12 -> formattedPhone.append(" ")
            }
            formattedPhone.append(digitsOnly[i])
        }

        return formattedPhone
    }
}