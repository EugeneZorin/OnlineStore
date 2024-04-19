package com.example.catalog.contract

import com.example.catalog.entity.Items

interface GetDataContract {
    suspend fun getDataUseCase(): Items
}
