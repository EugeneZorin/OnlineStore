package com.example.data.image.contract

interface RequestContract {
    suspend fun requestDatabase(): MutableList<String>
}