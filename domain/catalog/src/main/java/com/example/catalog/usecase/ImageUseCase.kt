package com.example.catalog.usecase

import com.example.catalog.contract.GetImageContract
import com.example.catalog.repository.GetImageRepository

class ImageUseCase(
    private val getImageRepository: GetImageRepository
): GetImageContract {
    override suspend fun getImage(): Map<String, Int> {
        return getImageRepository.getImage()
    }
}