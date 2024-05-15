package com.example.catalog.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalog.contract.GetDataContract
import com.example.catalog.contract.GetImageContract
import com.example.catalog.entity.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getDataContract: GetDataContract,
    private val getImageContract: GetImageContract
) : ViewModel() {

    private var _bitmap = MutableLiveData<MutableMap<String, Bitmap?>>(mutableMapOf())
    val bitmap: LiveData<MutableMap<String, Bitmap?>> get() = _bitmap

    private var _catalogItem = MutableLiveData<Items>()
    val catalogItem: MutableLiveData<Items> = _catalogItem
    fun getData() {
        viewModelScope.launch {
            _catalogItem.value = getDataContract.getDataUseCase()
        }
    }

    private fun loadImage(){
        viewModelScope.launch {
            val newBitmaps = mutableMapOf<String, Bitmap?>()
            getImageContract.getImage().let { byteArray ->
                byteArray.forEach { byteElement ->
                    newBitmaps[byteElement.key] = byteArrayToBitmap(byteElement.value)
                }
            }
            _bitmap.postValue(newBitmaps)
        }
    }

    private fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    init {
        getData()
        viewModelScope.launch {
            loadImage()
        }
    }





}
