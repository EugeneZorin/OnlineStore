package com.example.catalog.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catalog.databinding.CatalogItemBinding
import com.example.catalog.entity.Item
import com.example.catalog.entity.Items

class CatalogAdapter(
    private val info: Items,
) : RecyclerView.Adapter<CatalogAdapter.ItemHolder>() {

    private var chosenTag: String = ""


    @SuppressLint("NotifyDataSetChanged")
    fun updateChosenTag(tag: String) {
        chosenTag = tag
        notifyDataSetChanged()
    }


    class ItemHolder(private val binding: CatalogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
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
        return info.items.count { it.tags.contains(chosenTag) }
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val tagItems = info.items.filter { it.tags.contains(chosenTag) }
        holder.bind(tagItems[position])
    }
}

