package com.example.catalog.view.components

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.catalog.databinding.ActivityCatalogBinding
import com.example.catalog.entity.EntityData

class ButtonSetup(
    private val context: Context,
    private val binding: ActivityCatalogBinding,
    private val onTagSelected: (String) -> Unit
) {

    private val entityData = EntityData()
    private var choseTag: String = entityData.tagSeeAll


    fun initButtons() {
        settingButtons(
            colorButtonNorm = ContextCompat.getColor(context, com.example.core.R.color.light_grey),
            colorTextNorm = ContextCompat.getColor(context, com.example.core.R.color.grey),
            colorButtonAct = ContextCompat.getColor(context, com.example.core.R.color.dark_grey),
            colorTextAct = ContextCompat.getColor(context, com.example.core.R.color.white)
        )
        firstTag(
            colorButton = ContextCompat.getColor(context, com.example.core.R.color.dark_grey),
            colorText = ContextCompat.getColor(context, com.example.core.R.color.white)
        )
    }

    private fun firstTag(colorButton: Int, colorText: Int) {
        setupButtonColors(choseTag, colorButton, colorText)
    }

    private fun settingButtons(
        colorButtonNorm: Int,
        colorTextNorm: Int,
        colorButtonAct: Int,
        colorTextAct: Int
    ) {
        val buttonTagMap = mapOf(
            binding.allButton to entityData.tagSeeAll,
            binding.faceButton to entityData.tagFace,
            binding.bodyButton to entityData.tagBody,
            binding.suntanButton to entityData.tagSuntan,
            binding.maskButton to entityData.tagMask
        )

        buttonTagMap.forEach { (button, tag) ->
            button.setOnClickListener {
                if (tag != choseTag) {
                    onTagSelected(tag)
                    setupButtonColors(choseTag, colorButtonNorm, colorTextNorm)
                    choseTag = tag
                    setupButtonColors(choseTag, colorButtonAct, colorTextAct)
                }
            }
        }
    }

    private fun setupButtonColors(chosenTag: String, colorButton: Int, colorText: Int) {
        val buttonMap = mapOf(
            entityData.tagSeeAll to binding.allButton,
            entityData.tagFace to binding.faceButton,
            entityData.tagBody to binding.bodyButton,
            entityData.tagSuntan to binding.suntanButton,
            entityData.tagMask to binding.maskButton
        )

        buttonMap.forEach { (tag, button) ->
            if (tag == chosenTag) {
                button.setTextColor(colorText)
                button.setBackgroundColor(colorButton)
            }
        }
    }
}
