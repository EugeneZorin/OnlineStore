package com.example.data.image.usecase

import com.example.data.image.contract.RequestContract
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RequestDatabase: RequestContract {
    override fun requestDatabase(): MutableList<String> {

        val imageList = mutableListOf<String>()
        val database = Firebase.firestore
        database.collection("imageDatabase").get().addOnSuccessListener { images ->
            images.forEach {
                imageList.add(it.data.toString())
            }
        }
        return imageList
    }
}