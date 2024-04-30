package com.example.catalog.viewmodel

import android.content.Context
import androidx.core.content.ContextCompat
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

    private val tagData = TagData()
    private val choseTag: String = tagData.tagSeeAll

    suspend fun getData(): Items {
        return getDataContract.getDataUseCase()
    }


    fun buttons(choseTag: String, binding: ActivityCatalogBinding, context: Context) {

        val colorButtonAct = ContextCompat.getColor(context, R.color.dark_grey)
        val colorTextAct = ContextCompat.getColor(context, R.color.white)

        when (choseTag) {

            tagData.tagSeeAll -> {
                binding.allButton.setTextColor(colorTextAct)
                binding.allButton.setBackgroundColor(colorButtonAct)
            }

            tagData.tagFace -> {
                binding.faceButton.setTextColor(colorTextAct)
                binding.faceButton.setBackgroundColor(colorButtonAct)
            }

            tagData.tagBody -> {
                binding.bodyButton.setTextColor(colorTextAct)
                binding.bodyButton.setBackgroundColor(colorButtonAct)
            }

            tagData.tagSuntan -> {
                binding.suntanButton.setTextColor(colorTextAct)
                binding.suntanButton.setBackgroundColor(colorButtonAct)
            }

            tagData.tagMask -> {
                binding.maskButton.setTextColor(colorTextAct)
                binding.maskButton.setBackgroundColor(colorButtonAct)
            }
        }


    }


}
