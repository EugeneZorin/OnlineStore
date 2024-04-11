package com.example.catalog.contract

import com.example.catalog.entity.ItemsResponse

interface GetDataContract {
    suspend fun getDataUseCase(): ItemsResponse
}
