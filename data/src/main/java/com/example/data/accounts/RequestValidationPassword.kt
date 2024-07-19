package com.example.data.accounts

import com.example.data.entity.DatabaseEntity
import com.example.registration.repository.RequestValidationPasswordRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await
import org.mindrot.jbcrypt.BCrypt


private class RequestValidationPassword: RequestValidationPasswordRepository {

    private val databaseEntity: DatabaseEntity = DatabaseEntity()
    private val databaseReference =
        FirebaseDatabase.getInstance().getReference(databaseEntity.accountDatabase)

    override suspend fun requestValidationPassword(login: String, password: String): Boolean {
        return try {
            val snapshot =
                databaseReference.orderByChild(databaseEntity.numberPhone).equalTo(login).get()
                    .await()
            if (snapshot.exists()) {
                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue<DatabaseEntity>()
                    if (user != null && checkPassword(password, user.password)) {
                        return true
                    }
                }
            }
            false
        } catch (e: Exception) {

            false
        }
    }

    private fun checkPassword(plainPassword: String, hashedPassword: String): Boolean {
        return try {
            BCrypt.checkpw(plainPassword, hashedPassword)
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}

object RequestValidationPasswordFactory{
    fun create(): RequestValidationPasswordRepository{
        return RequestValidationPassword()
    }
}


