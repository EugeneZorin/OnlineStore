package com.example.data.goods.usecase

import android.content.Context
import android.util.Log
import com.example.catalog.entity.Item
import com.example.catalog.entity.Items
import com.example.catalog.repository.GetDataRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.IOException

class GetDataUseCase: GetDataRepository {

    override suspend fun getData(): Items {
        return withContext(Dispatchers.IO) {
            try {
                getDataFromFirebase() ?: Items(emptyList())
            } catch (ex: IOException) {
                throw ex
            }
        }
    }

    private suspend fun getDataFromFirebase(): Items? {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("items")
        return try {
            val snapshot = myRef.get().await()
            val itemList = snapshot.children.mapNotNull { it.getValue(Item::class.java) }
            val items = Items(itemList)
            items
        } catch (e: Exception) {
            null
        }
    }
}
