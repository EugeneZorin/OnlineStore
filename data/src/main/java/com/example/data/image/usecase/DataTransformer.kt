package com.example.data.image.usecase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.catalog.repository.GetDataTransformerRepository
import com.example.data.image.contract.RequestContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class DataTransformer(
    private val requestContract: RequestContract,
) : GetDataTransformerRepository {

    override suspend fun dataTransformer(): MutableList<ByteArray> {
        return withContext(Dispatchers.IO) {
            val result = mutableListOf<ByteArray>()
            ByteArrayOutputStream().use { stream ->
                requestContract.requestDatabase().forEach {
                    it.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    result.add(stream.toByteArray())
                }
                result
            }
        }
    }
}