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
    private val getImageContract: GetImageContract,
) : ViewModel() {

    private val _bitmap = MutableLiveData<Map<String, Bitmap?>>()
    private val _catalogItem = MutableLiveData<Items>()
    val bitmapAndCatalogItem = MediatorLiveData<Pair<Items, Map<String, Bitmap?>>>()

    private val loadingStatus = MutableLiveData<LoadingStatus>()
    private val errorMessage = MutableLiveData<String?>()

    init {
        bitmapAndCatalogItem.addSource(_catalogItem) { catalogItem ->
            val bitmaps = _bitmap.value ?: emptyMap()
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

        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            loadingStatus.value = LoadingStatus.LOADING
            try {
                val catalogItem = getDataContract.getDataUseCase()
                _catalogItem.value = catalogItem
                loadImages()
            } catch (e: Exception) {
                loadingStatus.value = LoadingStatus.ERROR
                errorMessage.value = e.message
            }
        }
    }

    private fun loadImages() {
        viewModelScope.launch {
            try {
                val byteArrayMap = getImageContract.getImage()
                val newBitmaps = byteArrayMap.mapValues { byteArrayToBitmap(it.value) }
                _bitmap.postValue(newBitmaps)
                loadingStatus.value = LoadingStatus.SUCCESS
            } catch (e: Exception) {
                loadingStatus.value = LoadingStatus.ERROR
                errorMessage.value = e.message
            }
        }
    }

    private fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    enum class LoadingStatus {
        LOADING, SUCCESS, ERROR
    }
}

