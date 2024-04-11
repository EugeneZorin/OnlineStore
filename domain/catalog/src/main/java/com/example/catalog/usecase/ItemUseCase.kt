package com.example.catalog.usecase

import com.example.catalog.contract.GetDataContract
import com.example.catalog.entity.ItemsResponse
import com.example.catalog.repository.GetDataRepository

class ItemUseCase(
    private val getDataRepository: GetDataRepository
): GetDataContract {

    override suspend fun getDataUseCase(): ItemsResponse {
        return getDataRepository.getData()
    }

}