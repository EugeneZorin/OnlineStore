package com.example.data.goods.usecase

import com.example.catalog.entity.ItemsDomain
import com.example.catalog.repository.GetDataRepository
import com.google.gson.Gson
import java.io.File

class GetData(): GetDataRepository {

    override suspend fun getData(): ItemsDomain {

        val jsonFilePath = "src/main/resources/data.json"
        val jsonString = File(jsonFilePath).readText()
        return Gson().fromJson(jsonString, ItemsDomain::class.java)
    }
}