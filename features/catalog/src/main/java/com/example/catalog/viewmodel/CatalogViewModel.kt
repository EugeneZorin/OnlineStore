package com.example.catalog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catalog.contract.GetDataContract
import com.example.catalog.entity.Items
import com.example.catalog.entity.EntityData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getDataContract: GetDataContract
) : ViewModel() {

    private val _choseTag = MutableLiveData<EntityData>()
    private val choseTag: MutableLiveData<EntityData> = _choseTag

    suspend fun getData(): Items {
        return getDataContract.getDataUseCase()
    }



}
