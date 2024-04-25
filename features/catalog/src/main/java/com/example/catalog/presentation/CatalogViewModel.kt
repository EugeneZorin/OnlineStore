package com.example.catalog.presentation

import androidx.lifecycle.ViewModel
import com.example.catalog.contract.GetDataContract
import com.example.catalog.entity.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getDataContract: GetDataContract
): ViewModel() {


    suspend fun getData(): Items {
        return getDataContract.getDataUseCase()
    }



}
