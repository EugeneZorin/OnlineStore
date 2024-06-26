package com.example.data.accounts.usecase

import android.util.Log
import com.example.registration.repository.register.RegistrationRepository
import com.google.firebase.database.FirebaseDatabase
import org.mindrot.jbcrypt.BCrypt

class Registration() : RegistrationRepository {

    override suspend fun registration(
        name: String,
        surname: String,
        numberPhone: String,
        password: String
    ) {

        val hashedPassword = hashPassword(password)
        val databaseReference = FirebaseDatabase.getInstance().getReference("users")

        val userId = databaseReference.push().key ?: return

        val user = mapOf(
            "name" to name,
            "surname" to surname,
            "numberPhone" to numberPhone,
            "password" to hashedPassword
        )

        databaseReference.child(userId).setValue(user)
            .addOnSuccessListener {
                Log.d("Registration: ","successfully")
            }
            .addOnFailureListener {
                Log.d("Registration: ","failure")
            }

    }

    private fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }


}