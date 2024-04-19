package com.example.catalog.repository

import com.example.catalog.entity.Items



interface GetDataRepository {
    suspend fun getData(): Items
}