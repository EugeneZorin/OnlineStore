package com.example.data.goods.usecase

import ItemsResponse
import com.google.gson.Gson
import java.io.File

class GetData() {

    fun getData(): ItemsResponse{

        val jsonFilePath = "src/main/resources/data.json"
        val jsonString = File(jsonFilePath).readText()

        return Gson().fromJson(jsonString, ItemsResponse::class.java)
    }
}