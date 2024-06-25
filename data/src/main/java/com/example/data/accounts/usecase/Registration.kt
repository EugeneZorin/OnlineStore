package com.example.data.accounts.usecase

import android.util.Log
import com.example.registration.repository.register.RegistrationRepository
import com.google.firebase.database.FirebaseDatabase
import org.mindrot.jbcrypt.BCrypt

class Registration() : RegistrationRepository {

    override suspend fun registration(
        numberPhone: String,
        password: String,
        name: String,
        surname: String
    ) {

        val hashedPassword = hashPassword(password)
        val databaseReference = FirebaseDatabase.getInstance().getReference("users")

        val userId = databaseReference.push().key ?: return

        val user = mapOf(
            "password" to hashedPassword,
            "number" to numberPhone,
            "name" to name,
            "surname" to surname
        )

        databaseReference.child(userId).setValue(user)
            .addOnSuccessListener {
            Log.d("NICE","NICE")
            }
            .addOnFailureListener {
                Log.d("LOSER","LOSER")
            }

    }

    private fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }


}