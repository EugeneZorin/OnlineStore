package com.example.data.accounts

import android.util.Log
import com.example.data.entity.DatabaseEntity
import com.example.registration.repository.RegistrationRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import kotlinx.coroutines.suspendCancellableCoroutine
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject
import kotlin.coroutines.resume

class Registration @Inject constructor() : RegistrationRepository {

    private val databaseEntity: DatabaseEntity = DatabaseEntity()

    override suspend fun registration(
        name: String,
        surname: String,
        numberPhone: String,
        password: String
    ): String? {

        val auth: FirebaseAuth = Firebase.auth
        val database: DatabaseReference = Firebase.database.reference
        val hashedPassword = hashPassword(password)
        val email = transformNumberToEmail(numberPhone)

        val user = mapOf(
            databaseEntity.name to name,
            databaseEntity.surname to surname,
            databaseEntity.email to email,
            databaseEntity.password to hashedPassword
        )

        return try {
            createUser(auth, email, hashedPassword)
            val currentUser = auth.currentUser
            currentUser?.let {
                setUserData(database, it.uid, user)
            }
        } catch (e: Exception) {
            Log.d(databaseEntity.errorCreateUser, "${e.message}")
            databaseEntity.errorCreateUser
        }


    }

    private suspend fun createUser(auth: FirebaseAuth, email: String, password: String): String {
        return suspendCancellableCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    continuation.resume(databaseEntity.createAccounts)
                }
                .addOnFailureListener { exception ->
                    continuation.resume( exception.message.toString() )
                }
        }
    }

    private suspend fun setUserData(database: DatabaseReference, uid: String, user: Map<String, String>): String {
        return suspendCancellableCoroutine { continuation ->
            database.child(databaseEntity.databaseAccounts).child(uid).setValue(user)
                .addOnSuccessListener {
                    continuation.resume(databaseEntity.setDataAccounts)
                }
                .addOnFailureListener { exception ->
                    continuation.resume(exception.message.toString())
                }

        }
    }

    private fun transformNumberToEmail(numberPhone: String): String {
        return "$numberPhone@number.com".replace(" ", "")
    }

    private fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }


}