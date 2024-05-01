package com.example.catalog.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.Checkable
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catalog.R
import com.example.catalog.databinding.ActivityCatalogBinding
import com.example.catalog.entity.TagData
import com.example.catalog.viewmodel.CatalogViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding
    private val viewModel: CatalogViewModel by viewModels()

    private lateinit var adapter: CatalogAdapter

    private val tagData = TagData()
    private var choseTag: String = tagData.tagSeeAll

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)


        init()

    }

    private fun init() {

        // Color for tag buttons
        val colorButtonAct = ContextCompat.getColor(this, R.color.dark_grey)
        val colorTextAct = ContextCompat.getColor(this, R.color.white)

        val colorButtonNorm = ContextCompat.getColor(this, R.color.light_grey)
        val colorTextNorm = ContextCompat.getColor(this, R.color.grey)


        binding.catalogItem.layoutManager = GridLayoutManager(this, 2)

        // CatalogAdapter startup
        CoroutineScope(Dispatchers.Main).launch {
            adapter = CatalogAdapter(viewModel.getData())
            adapter.updateChosenTag(tagData.tagSeeAll)
            binding.catalogItem.adapter = adapter
        }

        // Setting the first main tag (see all)
        firstTag(
            colorButton = colorButtonAct,
            colorText = colorTextAct
        )

        // Initializing tag selection buttons
        settingButtons(
            colorButtonNorm = colorButtonNorm,
            colorTextNorm = colorTextNorm,
            colorButtonAct = colorButtonAct,
            colorTextAct = colorTextAct
        )
    }

    private fun firstTag(colorButton: Int, colorText: Int) {
        buttonsFlags(choseTag, colorButton, colorText)
    }

    private fun settingButtons(
        colorButtonNorm: Int,
        colorTextNorm: Int,
        colorButtonAct: Int,
        colorTextAct: Int
    ) {
        binding.allButton.setOnClickListener {
            if (tagData.tagSeeAll != choseTag) {
                adapter.updateChosenTag(tagData.tagSeeAll)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = tagData.tagSeeAll
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.faceButton.setOnClickListener {
            if (tagData.tagFace != choseTag) {
                adapter.updateChosenTag(tagData.tagFace)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = tagData.tagFace
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.bodyButton.setOnClickListener {
            if (tagData.tagBody != choseTag) {
                adapter.updateChosenTag(tagData.tagBody)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = tagData.tagBody
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.suntanButton.setOnClickListener {
            if (tagData.tagSuntan != choseTag) {
                adapter.updateChosenTag(tagData.tagSuntan)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = tagData.tagSuntan
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.maskButton.setOnClickListener {
            if (tagData.tagMask != choseTag) {
                adapter.updateChosenTag(tagData.tagMask)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = tagData.tagMask
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

    }


    // Button color switching
    private fun buttonsFlags(choseTag: String, colorButton: Int, colorText: Int) {

        when (choseTag) {

            tagData.tagSeeAll -> {
                binding.allButton.setTextColor(colorText)
                binding.allButton.setBackgroundColor(colorButton)
            }

            tagData.tagFace -> {
                binding.faceButton.setTextColor(colorText)
                binding.faceButton.setBackgroundColor(colorButton)
            }

            tagData.tagBody -> {
                binding.bodyButton.setTextColor(colorText)
                binding.bodyButton.setBackgroundColor(colorButton)
            }

            tagData.tagSuntan -> {
                binding.suntanButton.setTextColor(colorText)
                binding.suntanButton.setBackgroundColor(colorButton)
            }

            tagData.tagMask -> {
                binding.maskButton.setTextColor(colorText)
                binding.maskButton.setBackgroundColor(colorButton)
            }
        }


    }


    /* private fun showSortMenu() {

         val popupMenu = PopupMenu(this, binding.sortButton)
         popupMenu.menuInflater.inflate(R.menu.sort_menu, popupMenu.menu)
         popupMenu.setOnMenuItemClickListener { menuItem ->
             when (menuItem.itemId) {
                 R.id.sortByPopularity -> sortByPopularity()
                 R.id.sortByPriceLowToHigh -> sortByPriceLowToHigh()
                 R.id.sortByPriceHighToLow -> sortByPriceHighToLow()
             }
             true
         }
         popupMenu.show()
     }

     private fun sortByPopularity() {

     }

     private fun sortByPriceLowToHigh() {

     }

     private fun sortByPriceHighToLow() {

     }*/

}


