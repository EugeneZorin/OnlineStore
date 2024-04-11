package com.example.data.accounts.usecase

import com.example.data.accounts.mapper.UserMapper
import com.example.data.accounts.room.UserDao
import com.example.registration.entities.SavingDataEntity
import com.example.registration.repository.saving.DataSavingRepository

class RegistrationImpl(
    private val userDao: UserDao
): DataSavingRepository {

    override suspend fun insert(savingDataEntity: SavingDataEntity) {
        val user = UserMapper.mapToUser(savingDataEntity)
        userDao.insert(user)
    }

    override suspend fun searchNumber(numberPhone: String): Boolean {
        return userDao.getNumber(numberPhone)
    }

    override suspend fun getUser(number: String): SavingDataEntity {
        val user = userDao.getUser(number)
        return UserMapper.mapToSavingDataEntity(user)
    }

    override suspend fun delete(number: String) {
        userDao.delete(number)
    }


}

