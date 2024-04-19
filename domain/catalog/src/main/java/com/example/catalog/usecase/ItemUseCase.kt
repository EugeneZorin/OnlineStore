package com.example.catalog.usecase

import com.example.catalog.contract.GetDataContract
import com.example.catalog.entity.Items
import com.example.catalog.repository.GetDataRepository

class ItemUseCase(
    private val getDataRepository: GetDataRepository
): GetDataContract {

    override suspend fun getDataUseCase(): Items {
        try {
            return getDataRepository.getData()
        } catch (e: Exception) {
            println("error $e")
        }
       return Items(
           items = listOf()
       )
    }

}