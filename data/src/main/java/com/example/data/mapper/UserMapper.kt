package com.example.data.mapper

import com.example.data.room.User
import com.example.registration.entities.SavingDataEntity

object UserMapper {
    fun mapToUser(savingDataEntity: SavingDataEntity): User {
        return User(
            name = savingDataEntity.name,
            surname = savingDataEntity.surname,
            numberPhone = savingDataEntity.numberPhone
        )
    }

    fun mapToSavingDataEntity(user: User): SavingDataEntity {
        return SavingDataEntity(
            name = user.name,
            surname = user.surname,
            numberPhone = user.numberPhone
        )
    }
}