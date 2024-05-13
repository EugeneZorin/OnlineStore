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

    private lateinit var adapterCatalog: CatalogAdapter
    private lateinit var adapterImage: ImageAdapter

    private val entityData = EntityData()
    private var choseTag: String = entityData.tagSeeAll

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)




        init()

    }

    private fun imageManager(){
       /* CoroutineScope(Dispatchers.Main).launch {
            adapterImage = ImageAdapter(this, viewModel.getImage())
        }
*/
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
            adapterCatalog = CatalogAdapter(viewModel.getData())
            adapterCatalog.updateChosenTag(entityData.tagSeeAll)
            binding.catalogItem.adapter = adapterCatalog
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
            if (entityData.tagSeeAll != choseTag) {
                adapterCatalog.updateChosenTag(entityData.tagSeeAll)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = entityData.tagSeeAll
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.faceButton.setOnClickListener {
            if (entityData.tagFace != choseTag) {
                adapterCatalog.updateChosenTag(entityData.tagFace)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = entityData.tagFace
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.bodyButton.setOnClickListener {
            if (entityData.tagBody != choseTag) {
                adapterCatalog.updateChosenTag(entityData.tagBody)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = entityData.tagBody
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.suntanButton.setOnClickListener {
            if (entityData.tagSuntan != choseTag) {
                adapterCatalog.updateChosenTag(entityData.tagSuntan)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = entityData.tagSuntan
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.maskButton.setOnClickListener {
            if (entityData.tagMask != choseTag) {
                adapterCatalog.updateChosenTag(entityData.tagMask)
                buttonsFlags(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = entityData.tagMask
                buttonsFlags(choseTag, colorButtonAct, colorTextAct)
            }
        }

    }


    // Button color switching
    private fun buttonsFlags(choseTag: String, colorButton: Int, colorText: Int) {

        when (choseTag) {

            entityData.tagSeeAll -> {
                binding.allButton.setTextColor(colorText)
                binding.allButton.setBackgroundColor(colorButton)
            }

            entityData.tagFace -> {
                binding.faceButton.setTextColor(colorText)
                binding.faceButton.setBackgroundColor(colorButton)
            }

            entityData.tagBody -> {
                binding.bodyButton.setTextColor(colorText)
                binding.bodyButton.setBackgroundColor(colorButton)
            }

            entityData.tagSuntan -> {
                binding.suntanButton.setTextColor(colorText)
                binding.suntanButton.setBackgroundColor(colorButton)
            }

            entityData.tagMask -> {
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
         adapterCatalog.updateChosenFilter(entityData.byPopularity)
     }

     private fun sortByPriceLowToHigh() {
         binding.sortButton.setText(R.string.by_price_reduction)
         adapterCatalog.updateChosenFilter(entityData.byPrice)
     }

     private fun sortByPriceHighToLow() {
         binding.sortButton.setText(R.string.on_price_increases)
         adapterCatalog.updateChosenFilter(entityData.onPrice)
     }

}


