package com.example.data.image.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.data.image.contract.RequestContract
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.io.IOException
import java.net.URL

class RequestDatabase: RequestContract {
    override suspend fun requestDatabase(): MutableList<Bitmap> {
        val database = Firebase.firestore
        val imageList = mutableListOf<Bitmap>()
        try {
            val images = database.collection("imageDatabase").get().await()
            images.forEach { document ->
                document.data.forEach { data ->
                    try {
                        val url = URL(data.value.toString())
                        val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                        imageList.add(image)
                    } catch (e: IOException) {
                        println(e)
                    }
                }
            }
        } catch (e: Exception) {
            println("Error fetching data: ${e.message}")
        }
        return imageList
    }
}