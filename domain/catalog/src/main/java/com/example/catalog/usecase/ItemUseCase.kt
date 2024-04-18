package com.example.catalog.usecase

import com.example.catalog.contract.GetDataContract
import com.example.catalog.entity.ItemsDomain
import com.example.catalog.repository.GetDataRepository

class ItemUseCase(
    private val getDataRepository: GetDataRepository
): GetDataContract {

    override suspend fun getDataUseCase(): ItemsDomain {
        return getDataRepository.getData()
    }

}