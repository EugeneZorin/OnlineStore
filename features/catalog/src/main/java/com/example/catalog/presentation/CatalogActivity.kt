package com.example.catalog.presentation

import android.os.Bundle
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding
    private val viewModel: CatalogViewModel by viewModels()

    private lateinit var adapter: CatalogAdapter

    private val emptyData = EntityData()
    private var choseTag: String = emptyData.tagSeeAll

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


        binding.sortButton.setOnClickListener {
            showSortMenu()
        }

        // CatalogAdapter startup
        CoroutineScope(Dispatchers.Main).launch {
            adapter = CatalogAdapter(viewModel.getData())
            adapter.updateChosenTag(emptyData.tagSeeAll)
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
            if (emptyData.tagSeeAll != choseTag) {
                adapter.updateChosenTag(emptyData.tagSeeAll)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = emptyData.tagSeeAll
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.faceButton.setOnClickListener {
            if (emptyData.tagFace != choseTag) {
                adapter.updateChosenTag(emptyData.tagFace)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = emptyData.tagFace
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.bodyButton.setOnClickListener {
            if (emptyData.tagBody != choseTag) {
                adapter.updateChosenTag(emptyData.tagBody)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = emptyData.tagBody
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.suntanButton.setOnClickListener {
            if (emptyData.tagSuntan != choseTag) {
                adapter.updateChosenTag(emptyData.tagSuntan)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = emptyData.tagSuntan
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.maskButton.setOnClickListener {
            if (emptyData.tagMask != choseTag) {
                adapter.updateChosenTag(emptyData.tagMask)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = emptyData.tagMask
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

    }


    // Button color switching
    private fun buttonsFlags(choseTag: String, colorButton: Int, colorText: Int) {

        when (choseTag) {

            emptyData.tagSeeAll -> {
                binding.allButton.setTextColor(colorText)
                binding.allButton.setBackgroundColor(colorButton)
            }

            emptyData.tagFace -> {
                binding.faceButton.setTextColor(colorText)
                binding.faceButton.setBackgroundColor(colorButton)
            }

            emptyData.tagBody -> {
                binding.bodyButton.setTextColor(colorText)
                binding.bodyButton.setBackgroundColor(colorButton)
            }

            emptyData.tagSuntan -> {
                binding.suntanButton.setTextColor(colorText)
                binding.suntanButton.setBackgroundColor(colorButton)
            }

            emptyData.tagMask -> {
                binding.maskButton.setTextColor(colorText)
                binding.maskButton.setBackgroundColor(colorButton)
            }
        }


    }


     private fun showSortMenu() {

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
         binding.sortButton.setText(R.string.by_popularity)
         adapter.updateChosenFilter(emptyData.byPopularity)
     }

     private fun sortByPriceLowToHigh() {
         binding.sortButton.setText(R.string.by_price_reduction)
         adapter.updateChosenFilter(emptyData.byPrice)
     }

     private fun sortByPriceHighToLow() {
         binding.sortButton.setText(R.string.on_price_increases)
         adapter.updateChosenFilter(emptyData.onPrice)
     }

}


