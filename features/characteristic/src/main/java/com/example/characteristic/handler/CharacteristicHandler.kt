package com.example.characteristic.handler

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import com.example.catalog.contract.NavigationCharacteristic
import com.example.catalog.entity.CatalogItem
import com.example.characteristic.entity.DataHolder
import com.example.characteristic.view.CharacteristicActivity
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