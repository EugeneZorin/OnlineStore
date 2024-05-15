package com.example.data.image.contract

import android.graphics.Bitmap

interface RequestContract {
    suspend fun requestDatabase():  MutableMap<String, Bitmap>
}