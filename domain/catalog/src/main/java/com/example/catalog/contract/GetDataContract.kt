package com.example.catalog.contract

import com.example.catalog.entity.ItemsDomain

interface GetDataContract {
    suspend fun getDataUseCase(): ItemsDomain
}
