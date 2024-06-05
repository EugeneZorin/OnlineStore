package com.example.characteristic

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import com.example.catalog.contract.NavigationCharacteristic
import com.example.catalog.entity.CatalogItem
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class CharacteristicHandler @Inject constructor(
    @ActivityContext private val context: Context
) : NavigationCharacteristic {
    override fun navigationCharacteristic(bitmap: Bitmap?, item: CatalogItem) {
        val intent = Intent(context, CharacteristicActivity::class.java).apply {
            putExtra("item", item)
        }

        context.startActivity(intent)

        DataHolder.bitmap = bitmap
    }

}