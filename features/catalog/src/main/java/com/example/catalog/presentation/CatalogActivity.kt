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
class CatalogActivity: AppCompatActivity() {

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


    private fun init(){

        val colorButtonAct = ContextCompat.getColor(this, R.color.dark_grey)
        val colorTextAct = ContextCompat.getColor(this, R.color.white)

        val colorButtonNorm = ContextCompat.getColor(this, R.color.light_grey)
        val colorTextNorm = ContextCompat.getColor(this, R.color.grey)

        binding.catalogItem.layoutManager =  GridLayoutManager(this, 2)

        CoroutineScope(Dispatchers.Main).launch {
            adapter = CatalogAdapter(viewModel.getData())
            binding.catalogItem.adapter = adapter
        }

        firstTag(colorButtonAct, colorTextAct)

        binding.allButton.setOnClickListener {
            if (tagData.tagSeeAll != choseTag) {
                adapter.updateChosenTag(tagData.tagSeeAll)
                buttons(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = tagData.tagSeeAll
                buttons(choseTag, colorButtonAct, colorTextAct)
            }
        }

        binding.faceButton.setOnClickListener {
            if (tagData.tagFace != choseTag){
                adapter.updateChosenTag(tagData.tagFace)
                buttons(choseTag, colorButtonNorm, colorTextNorm)
                choseTag = tagData.tagFace
                buttons(choseTag, colorButtonAct, colorTextAct)
            }
        }

    }

    private fun firstTag(colorButton: Int, colorText: Int){
        buttons(choseTag, colorButton, colorText)
    }


    private fun buttons(choseTag: String, colorButton: Int, colorText: Int) {

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


