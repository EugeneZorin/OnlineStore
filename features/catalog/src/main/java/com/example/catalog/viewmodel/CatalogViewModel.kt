package com.example.catalog.viewmodel

import androidx.lifecycle.ViewModel
import com.example.catalog.contract.GetDataContract
import com.example.catalog.contract.GetImageContract
import com.example.catalog.entity.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getDataContract: GetDataContract,
    private val getImageContract: GetImageContract
) : ViewModel() {

    suspend fun getData(): Items {
        return getDataContract.getDataUseCase()
    }

    suspend fun getImage(): Map<String, Int> {
        return getImageContract.getImage()
    }



}
