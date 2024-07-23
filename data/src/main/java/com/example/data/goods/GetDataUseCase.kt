package com.example.data.goods

import com.example.catalog.entity.Item
import com.example.catalog.entity.Items
import com.example.catalog.repository.GetDataRepository
import com.example.data.entity.DatabaseEntity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GetDataUseCase @Inject constructor(): GetDataRepository {

    private val databaseEntity: DatabaseEntity = DatabaseEntity()

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
        val myRef = database.getReference(databaseEntity.itemDatabase)
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
