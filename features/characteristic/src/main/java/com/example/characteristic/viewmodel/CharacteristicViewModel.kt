package com.example.characteristic.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.characteristic.entity.DataHolder

class CharacteristicViewModel(

) : ViewModel() {

    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap> get() = _bitmap

    init {
        _bitmap.value = DataHolder.bitmap
    }


}