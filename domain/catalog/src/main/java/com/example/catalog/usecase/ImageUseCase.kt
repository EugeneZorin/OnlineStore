package com.example.catalog.usecase

import com.example.catalog.contract.GetImageContract
import com.example.catalog.repository.GetDataTransformerRepository

class ImageUseCase(
    private val getDataTransformerRepository: GetDataTransformerRepository
): GetImageContract {
    override suspend fun getImage(): ByteArray {
        return getDataTransformerRepository.dataTransformer()
    }
}