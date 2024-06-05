package com.example.catalog.contract

import android.graphics.Bitmap
import com.example.catalog.entity.CatalogItem


interface NavigationCharacteristic {
    fun navigationCharacteristic(bitmap: Bitmap?, item: CatalogItem)
}