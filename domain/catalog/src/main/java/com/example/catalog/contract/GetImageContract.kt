package com.example.catalog.contract

import com.example.catalog.entity.Items

interface GetImageContract {
    suspend fun getImage(): Map<String, Int>
}