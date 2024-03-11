package com.example.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name" ) val name: String,
    @ColumnInfo(name = "surname" ) val surname: String,
    @ColumnInfo(name = "number_phone" ) val numberPhone: String
)