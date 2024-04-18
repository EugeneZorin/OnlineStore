package com.example.catalog.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalog.contract.GetDataContract
import com.example.catalog.entity.ItemsDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getDataContract: GetDataContract
): ViewModel() {

    init {
        viewModelScope.launch {
            Log.d("123","${ getDataContract.getDataUseCase().items}")

        }
    }

}