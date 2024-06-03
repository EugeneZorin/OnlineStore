package com.example.characteristic

import android.content.Context
import android.content.Intent
import com.example.catalog.contract.NavigationCharacteristic
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class CharacteristicHandler @Inject constructor(
    @ActivityContext private val context: Context
) : NavigationCharacteristic {
    override fun navigationCharacteristic() {
        val intent = Intent(context, CharacteristicActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}