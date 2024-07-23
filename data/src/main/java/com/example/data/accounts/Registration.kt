package com.example.data.accounts

import android.util.Log
import com.example.data.entity.DatabaseEntity
import com.example.registration.repository.RegistrationRepository
import com.google.firebase.database.FirebaseDatabase
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

class Registration @Inject constructor() : RegistrationRepository {

    private val databaseEntity: DatabaseEntity = DatabaseEntity()

    override suspend fun registration(
        name: String,
        surname: String,
        numberPhone: String,
        password: String
    ) {

        val hashedPassword = hashPassword(password)
        val databaseReference = FirebaseDatabase.getInstance().getReference(databaseEntity.accountDatabase)

        val userId = databaseReference.push().key ?: return

        val user = mapOf(
            databaseEntity.name to name,
            databaseEntity.surname to surname,
            databaseEntity.numberPhone to numberPhone,
            databaseEntity.password to hashedPassword
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