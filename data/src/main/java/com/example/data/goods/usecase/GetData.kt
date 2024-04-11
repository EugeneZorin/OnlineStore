package com.example.data.goods.usecase


import com.example.catalog.entity.ItemsResponse
import com.example.catalog.repository.GetDataRepository
import com.google.gson.Gson
import java.io.File

class GetData(): GetDataRepository {

    override suspend fun getData(): ItemsResponse {

        val jsonFilePath = "src/main/resources/data.json"
        val jsonString = File(jsonFilePath).readText()

        return Gson().fromJson(jsonString, ItemsResponse::class.java)
    }
}