package com.example.catalog.viewmodel

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catalog.R
import com.example.catalog.contract.GetDataContract
import com.example.catalog.databinding.ActivityCatalogBinding
import com.example.catalog.entity.Items
import com.example.catalog.entity.TagData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getDataContract: GetDataContract
) : ViewModel() {

    private val _choseTag = MutableLiveData<TagData>()
    private val choseTag: MutableLiveData<TagData> = _choseTag

    suspend fun getData(): Items {
        return getDataContract.getDataUseCase()
    }



}
