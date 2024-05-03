package com.example.catalog.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catalog.databinding.CatalogItemBinding
import com.example.catalog.entity.Item
import com.example.catalog.entity.Items
import com.example.catalog.entity.EntityData

class CatalogAdapter(
    private val info: Items,
    private val image: Map<String, Int>,
) : RecyclerView.Adapter<CatalogAdapter.ItemHolder>() {

    private val entityData = EntityData()
    private var chosenTag: String = entityData.empty
    private var chosenFilter: String = entityData.empty
    private val seeAll = EntityData().tagSeeAll


    @SuppressLint("NotifyDataSetChanged")
    fun updateChosenTag(tag: String) {
        chosenTag = tag
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateChosenFilter(filter: String) {
        chosenFilter = filter
        notifyDataSetChanged()
    }


    class ItemHolder(private val binding: CatalogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Item, image: Map<String, Int>) {
            with(binding) {
                price.text = item.price.price
                priceWithDiscount.text = item.price.priceWithDiscount
                unit.text = item.price.unit
                discount.text = "-${item.price.discount} %"
                title.text = item.title
                descriptions.text = item.description
                rating.text = item.feedback.rating
                count.text = "(${item.feedback.count})"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CatalogItemBinding.inflate(inflater, parent, false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (chosenTag == seeAll) {
            info.items.size
        } else {
            info.items.count { it.tags.contains(chosenTag) }
        }
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val tagItems = if (chosenTag == seeAll) {
            info.items
        } else {
            info.items.filter { it.tags.contains(chosenTag) }
        }
        holder.bind(tagItems[position], image)
    }

}

