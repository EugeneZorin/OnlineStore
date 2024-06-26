package com.example.data.image.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.data.image.contract.RequestContract
import com.example.data.entity.DatabaseEntity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.io.IOException
import java.net.URL

class RequestDatabase : RequestContract {

    private val databaseEntity: DatabaseEntity = DatabaseEntity()

    override suspend fun requestDatabase(): MutableMap<String, Bitmap> {
        val database = Firebase.firestore
        val imageMap = mutableMapOf<String, Bitmap>()
        try {
            val images = database.collection(databaseEntity.imageDatabase).get().await()
            images.forEach { document ->
                document.data.forEach { data ->
                    try {
                        val url = URL(data.value.toString())
                        val id = data.key
                        val image =
                            BitmapFactory.decodeStream(url.openConnection().getInputStream())
                        imageMap[id] = image
                    } catch (e: IOException) {
                        println(e)
                    }
                }
            }
        } catch (e: Exception) {
            println("Error fetching data: ${e.message}")
        }
        return imageMap
    }
}