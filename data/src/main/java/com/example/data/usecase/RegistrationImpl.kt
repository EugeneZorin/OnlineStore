package com.example.data.usecase

import com.example.data.room.User
import com.example.data.room.UserDao
import com.example.data.room.UserDatabase
import com.example.registration.entities.SavingDataEntity
import com.example.registration.repository.saving.DataSavingRepository

class RegistrationImpl(
    private val userDao: UserDao
): DataSavingRepository {
    override suspend fun insert(savingDataEntity: SavingDataEntity) {
        userDao.insert(
            User(
                name = savingDataEntity.name,
                surname = savingDataEntity.surname,
                numberPhone = savingDataEntity.numberPhone
            )
        )
    }

    override suspend fun searchNumber(numberPhone: String): Boolean {
        return userDao.getNumber(numberPhone)
    }


    override suspend fun delete(id: String) {
        userDao.delete(id)
    }


}

