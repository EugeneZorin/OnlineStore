package com.example.data.image.usecase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
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
    private val context: Context
) : GetDataTransformerRepository {
    override suspend fun dataTransformer(): ByteArray {

        return withContext(Dispatchers.IO) {
            val data = requestContract.requestDatabase()
            val loader = ImageLoader(context)
            val request =
                ImageRequest.Builder(context)
                    .data(data)
                    .build()
            val result = loader.execute(request)

            (if (result is SuccessResult) {
                val bitmap = (result.drawable as? BitmapDrawable)?.bitmap
                bitmap?.let {
                    val stream = ByteArrayOutputStream()
                    it.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    stream.toByteArray()
                }
            } else {
                null
            })!!
        }
    }
}