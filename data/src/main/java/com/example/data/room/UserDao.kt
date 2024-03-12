package com.example.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT id FROM user_accounts WHERE number_phone = :number ")
    suspend fun getNumber(number: String): Boolean

    @Insert
    suspend fun insert(data: User)

    @Query("DELETE FROM user_accounts WHERE id = :user")
    suspend fun delete(user: String)
}