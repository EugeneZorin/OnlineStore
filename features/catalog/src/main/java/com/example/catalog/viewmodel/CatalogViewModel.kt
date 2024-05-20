package com.example.catalog.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalog.contract.GetDataContract
import com.example.catalog.contract.GetImageContract
import com.example.catalog.entity.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getDataContract: GetDataContract,
    private val getImageContract: GetImageContract
) : ViewModel() {

    private val _bitmap = MutableLiveData<MutableMap<String, Bitmap?>>().apply { value = mutableMapOf() }
    private val _catalogItem = MutableLiveData<Items>()

    val bitmapAndCatalogItem = MediatorLiveData<Pair<Items, Map<String, Bitmap?>>>()
    private fun getData() {
        viewModelScope.launch {
            _catalogItem.value = getDataContract.getDataUseCase()
        }
    }

    private fun loadImage() {
        viewModelScope.launch {
            val newBitmaps = mutableMapOf<String, Bitmap?>()
            getImageContract.getImage().let { byteArrayMap ->
                byteArrayMap.forEach { (key, value) ->
                    val bitmap = byteArrayToBitmap(value)
                    newBitmaps[key] = bitmap
                }
            }
            _bitmap.value = newBitmaps
        }
    }

    private fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    init {
        getData()
        loadImage()


        bitmapAndCatalogItem.addSource(_catalogItem) { catalogItem ->
            val bitmaps = _bitmap.value ?: mutableMapOf()
            if (catalogItem != null) {
                bitmapAndCatalogItem.value = Pair(catalogItem, bitmaps)
            }
        }

        bitmapAndCatalogItem.addSource(_bitmap) { bitmaps ->
            val items = _catalogItem.value
            if (items != null) {
                bitmapAndCatalogItem.value = Pair(items, bitmaps)
            }
        }

    }
}
