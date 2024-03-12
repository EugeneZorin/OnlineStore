package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities  = [
        User::class
    ]
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}