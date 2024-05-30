package com.example.data.image.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.data.image.contract.RequestContract
import com.example.data.image.entity.DatabaseEntity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.io.IOException
import java.net.URL

class RequestDatabase : RequestContract {

    override suspend fun requestDatabase(): MutableMap<String, Bitmap> {
        Firebase.database.setPersistenceEnabled(true)
        val scoresRef = Firebase.database.getReference("scores")
        scoresRef.keepSynced(true)
        val database = Firebase.firestore
        val imageMap = mutableMapOf<String, Bitmap>()
        try {
            val images = database.collection(DatabaseEntity().databaseName).get().await()
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