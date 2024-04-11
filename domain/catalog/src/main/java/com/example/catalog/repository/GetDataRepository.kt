package com.example.catalog.repository

import com.example.catalog.entity.ItemsResponse

interface GetDataRepository {
    suspend fun getData(): ItemsResponse
}