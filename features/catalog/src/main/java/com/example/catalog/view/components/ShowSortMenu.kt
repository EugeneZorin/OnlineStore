package com.example.catalog.view.components

import android.content.Context
import android.widget.PopupMenu
import com.example.catalog.R
import com.example.catalog.databinding.ActivityCatalogBinding
import com.example.catalog.entity.EntityData

class ShowSortMenu(
    private val binding: ActivityCatalogBinding,
    private val context: Context,
    private val onFilterSelected: (String) -> Unit
) {

    private val entityData = EntityData()
    fun showSortMenu() {
        PopupMenu(context, binding.sortButton).apply {
            menuInflater.inflate(R.menu.sort_menu, menu)
            setOnMenuItemClickListener { menuItem ->
                val chosenFilter = when (menuItem.itemId) {
                    R.id.sortByPopularity -> entityData.byPopularity
                    R.id.sortByPriceLowToHigh -> entityData.byPrice
                    R.id.sortByPriceHighToLow -> entityData.onPrice
                    else -> return@setOnMenuItemClickListener false
                }
                onFilterSelected(chosenFilter)
                binding.sortButton.text = menuItem.title
                true
            }
        }.show()
    }
}