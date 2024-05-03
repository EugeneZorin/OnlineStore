package com.example.catalog.repository

interface GetImageRepository {
    suspend fun getImage(): Map<String, Int>
}