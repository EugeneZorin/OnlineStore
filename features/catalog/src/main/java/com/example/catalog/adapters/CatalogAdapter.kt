package com.example.catalog.adapters

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catalog.contract.NavigationCharacteristic
import com.example.catalog.databinding.CatalogItemBinding
import com.example.catalog.entity.EntityData
import com.example.catalog.entity.Item
import com.example.catalog.entity.Items
import com.example.catalog.holder.ItemHolder

class CatalogAdapter(
    private val info: Items,
    private val bitmapMap: Map<String, Bitmap?>,
    private val navigationCharacteristic: NavigationCharacteristic
) : RecyclerView.Adapter<ItemHolder>() {

    private val entityData = EntityData()
    private var chosenTag: String = entityData.empty
    private var chosenFilter: String = entityData.byPopularity
    private var filteredItems: List<Item> = info.items

    @SuppressLint("NotifyDataSetChanged")
    fun updateChosenTag(tag: String) {
        chosenTag = tag
        filterAndSortItems()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateChosenFilter(filter: String) {
        chosenFilter = filter
        filterAndSortItems()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterAndSortItems() {
        filteredItems = if (chosenTag == entityData.tagSeeAll) {
            info.items
        } else {
            info.items.filter { it.tags.contains(chosenTag) }
        }
        applyFilters()
        notifyDataSetChanged()
    }

    private fun applyFilters() {
        filteredItems = when (chosenFilter) {
            entityData.byPopularity -> filteredItems.sortedByDescending { it.feedback.count }
            entityData.byPrice -> filteredItems.sortedByDescending { it.price.price.toInt() }
            entityData.onPrice -> filteredItems.sortedBy { it.price.price.toInt() }
            else -> filteredItems
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CatalogItemBinding.inflate(inflater, parent, false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int = filteredItems.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = filteredItems[position]
        val itemBitmap = bitmapMap[item.id]
        holder.bind(item, itemBitmap, navigationCharacteristic)
    }
}




