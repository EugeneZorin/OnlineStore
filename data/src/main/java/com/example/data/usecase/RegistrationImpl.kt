package com.example.data.usecase

import com.example.data.room.User
import com.example.data.room.UserDao
import com.example.data.room.UserDatabase
import com.example.registration.entities.SavingDataEntity
import com.example.registration.repository.saving.DataSavingRepository

class RegistrationImpl(
    private val userDatabase: UserDatabase
): DataSavingRepository {
    override suspend fun insert(savingDataEntity: SavingDataEntity) {
        userDatabase.userDao().insert(
            User(
                name = savingDataEntity.name,
                surname = savingDataEntity.surname,
                numberPhone = savingDataEntity.numberPhone
            )
        )
    }

    override suspend fun searchNumber(numberPhone: String): Boolean {
        return userDatabase.userDao().getNumber(numberPhone)
    }


    override suspend fun delete(id: String) {
        userDatabase.userDao().delete(id)
    }


}

