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

    override suspend fun dataTransformer(): MutableMap<String, ByteArray> {
        return withContext(Dispatchers.IO) {
            val result = mutableMapOf<String, ByteArray>()
            requestContract.requestDatabase().forEach { entry ->
                ByteArrayOutputStream().use { bitmap ->
                    entry.value.compress(Bitmap.CompressFormat.PNG, 100, bitmap)
                    result[entry.key] = bitmap.toByteArray()
                }
            }
            result
        }
    }
}
