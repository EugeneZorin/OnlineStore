package com.example.catalog.presentation

import android.os.Bundle
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catalog.R
import com.example.catalog.databinding.ActivityCatalogBinding
import com.example.catalog.viewmodel.CatalogViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatalogActivity: AppCompatActivity() {


    private lateinit var binding: ActivityCatalogBinding
    private val viewModel: CatalogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun init(){
        binding.catalogItem.layoutManager =  GridLayoutManager(this, 2)
        CoroutineScope(Dispatchers.Main).launch {
            binding.catalogItem.adapter =  CatalogAdapter(viewModel.getData())
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