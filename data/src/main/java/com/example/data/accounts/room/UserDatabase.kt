package com.example.data.accounts.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities  = [
        User::class
    ]
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun database(application: Context): UserDatabase {
            return Room.databaseBuilder(
                application,
                UserDatabase::class.java,
                "user_database"
            ).build()
        }
    }
}