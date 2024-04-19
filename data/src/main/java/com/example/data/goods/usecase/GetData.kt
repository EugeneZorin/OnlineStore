package com.example.data.goods.usecase

import android.content.Context
import com.example.catalog.entity.Items
import com.example.catalog.repository.GetDataRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class GetData(private val context: Context): GetDataRepository {

    override suspend fun getData(): Items {
        return withContext(Dispatchers.IO) {
            try {

                val inputStream = context.assets.open("data.json")

                val jsonString = inputStream.bufferedReader().use { it.readText() }

                Gson().fromJson(jsonString, Items::class.java)
            } catch (ex: IOException) {

                throw ex
            }
        }
    }
}