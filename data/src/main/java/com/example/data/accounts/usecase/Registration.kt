package com.example.data.accounts.usecase

import android.util.Log
import com.example.registration.repository.register.RegistrationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Registration(): RegistrationRepository {

    private lateinit var auth: FirebaseAuth

    override suspend fun registration(numberPhone: String, password: String){

        auth = Firebase.auth

        auth.createUserWithEmailAndPassword(numberPhone, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("TAG isSuccessful", "createUserWithEmail:success")
            } else {
                Log.w("TAG exception", "createUserWithEmail:failure", task.exception)
            }
        }

    }


}