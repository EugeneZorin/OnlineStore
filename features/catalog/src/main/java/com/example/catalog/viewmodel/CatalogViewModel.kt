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
import javax.inject.Inject


@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getDataContract: GetDataContract,
    private val getImageContract: GetImageContract
) : ViewModel() {

    private val _bitmap = MutableLiveData<Bitmap?>()
    val bitmap: LiveData<Bitmap?> get() = _bitmap
    suspend fun getData(): Items {
        return getDataContract.getDataUseCase()
    }

    private suspend fun loadImage() {
        viewModelScope.launch {
            val byteArray = getImageContract.getImage()
            byteArray.let {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                _bitmap.postValue(bitmap)
            } ?: run {
                _bitmap.postValue(null)
            }
        }
    }

    init {
        viewModelScope.launch {
            loadImage()
            Log.d("asdfasf","${bitmap.value}")
        }
    }



}
