package com.example.catalog.repository

interface GetDataTransformerRepository {
    suspend fun dataTransformer(): ByteArray
}