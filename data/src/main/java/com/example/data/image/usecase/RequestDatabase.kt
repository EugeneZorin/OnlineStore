package com.example.data.image.usecase

import android.util.Log
import com.example.data.image.contract.RequestContract
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RequestDatabase: RequestContract {

    private val database = Firebase.firestore
    override suspend fun requestDatabase(): MutableList<String> {
        return try {
            val imageList = mutableListOf<String>()
            database.collection("imageDatabase").get().addOnSuccessListener { images ->
                images.forEach {
                    imageList.add(it.data.toString())
                }
            }
            imageList
        } finally {

        }


        /*val imageList = mutableListOf<String>()

        database.collection("imageDatabase").get().addOnSuccessListener { images ->
            images.forEach {
                imageList.add(it.data.toString())
            }
        }
        return imageList*/
    }
}