package com.example.catalog.presentation

import android.os.Bundle
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catalog.R
import com.example.catalog.databinding.ActivityCatalogBinding


class CatalogActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding
    private val catalogViewModel:CatalogViewModel by viewModels()
    private val adapter = CatalogAdapter(catalogViewModel)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }


    private fun init(){
        binding.catalogItem.layoutManager =  GridLayoutManager(this, 2)
        binding.catalogItem.adapter = adapter

        binding.sortButton.setOnClickListener {
            showSortMenu()
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
        // Осуществить сортировку товаров по популярности (по рейтингу)
        // от большего значения к меньшему
    }

    private fun sortByPriceLowToHigh() {
        // Осуществить сортировку товаров по увеличению цены
        // от меньшего значения к большему
    }

    private fun sortByPriceHighToLow() {
        // Осуществить сортировку товаров по уменьшению цены
        // от большего значения к меньшему
    }

}