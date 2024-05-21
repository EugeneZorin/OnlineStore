package com.example.catalog.presentation

import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catalog.R
import com.example.catalog.databinding.ActivityCatalogBinding
import com.example.catalog.entity.EntityData
import com.example.catalog.viewmodel.CatalogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding
    private val viewModel: CatalogViewModel by viewModels()

    private lateinit var adapterCatalog: CatalogAdapter

    private val entityData = EntityData()
    private var choseTag: String = entityData.tagSeeAll

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {

        binding.catalogItem.layoutManager = GridLayoutManager(this, 2)

        binding.sortButton.setOnClickListener {
            showSortMenu()
        }

        // CatalogAdapter startup
        viewModel.bitmapAndCatalogItem.observe(this) { (data, map) ->
            adapterCatalog = CatalogAdapter(data, map)
            adapterCatalog.updateChosenTag(entityData.tagSeeAll)
            binding.catalogItem.adapter = adapterCatalog
        }


        // Initializing tag selection buttons
        settingButtons(
            colorButtonNorm = ContextCompat.getColor(this, R.color.light_grey),
            colorTextNorm = ContextCompat.getColor(this, R.color.grey),
            colorButtonAct = ContextCompat.getColor(this, R.color.dark_grey),
            colorTextAct = ContextCompat.getColor(this, R.color.white)
        )


        // Setting the first main tag (see all)
        firstTag(
            colorButton = ContextCompat.getColor(this, R.color.dark_grey),
            colorText = ContextCompat.getColor(this, R.color.white)
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
                    adapterCatalog.updateChosenTag(tag)
                    setupButtonColors(choseTag, colorButtonNorm, colorTextNorm)
                    choseTag = tag
                    setupButtonColors(choseTag, colorButtonAct, colorTextAct)
                }
            }
        }
    }

    // Button color switching
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

    private fun showSortMenu() {
        PopupMenu(this, binding.sortButton).apply {
            menuInflater.inflate(R.menu.sort_menu, menu)
            setOnMenuItemClickListener { menuItem ->
                val chosenFilter = when (menuItem.itemId) {
                    R.id.sortByPopularity -> entityData.byPopularity
                    R.id.sortByPriceLowToHigh -> entityData.byPrice
                    R.id.sortByPriceHighToLow -> entityData.onPrice
                    else -> return@setOnMenuItemClickListener false
                }
                adapterCatalog.updateChosenFilter(chosenFilter)
                binding.sortButton.text = menuItem.title
                true
            }
        }.show()
    }
}


