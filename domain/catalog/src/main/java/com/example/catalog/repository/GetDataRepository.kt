package com.example.catalog.repository

import com.example.catalog.entity.ItemsDomain


interface GetDataRepository {
    suspend fun getData(): ItemsDomain
}