package com.example.data.accounts.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT id FROM user_accounts WHERE number_phone = :number ")
    suspend fun getNumber(number: String): Boolean

    @Insert
    suspend fun insert(data: User)

    @Query("DELETE FROM user_accounts WHERE number_phone = :number")
    suspend fun delete(number: String)

    @Query("SELECT * FROM user_accounts WHERE number_phone= :number")
    suspend fun getUser(number: String): User
}