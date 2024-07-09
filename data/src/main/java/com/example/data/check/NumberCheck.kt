package com.example.data.check

import com.example.data.entity.DatabaseEntity
import com.example.registration.repository.register.ValidationNumberPhoneRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NumberCheck(): ValidationNumberPhoneRepository {

    private val databaseEntity: DatabaseEntity = DatabaseEntity()

    override suspend fun numberCheck(numberPhoneValidation: String): Boolean {
        return suspendCoroutine { continuation ->
            val databaseReference = FirebaseDatabase.getInstance().getReference(databaseEntity.accountDatabase)

            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var result = true
                    snapshot.children.forEach {
                        if (it.child(databaseEntity.numberPhone).value == numberPhoneValidation) {
                            result = false
                        }
                    }
                    continuation.resume(result)
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resume(false)
                }
            })
        }
    }
}

