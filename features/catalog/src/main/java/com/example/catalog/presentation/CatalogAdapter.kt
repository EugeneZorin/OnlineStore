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
    private val info: Items
) : RecyclerView.Adapter<CatalogAdapter.ItemHolder>() {

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

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = info.items[position]
        if (item.tags.contains("body")) {
            holder.bind(item)
        }

    }

    private fun filtersTags(tag: String): Int {
        var element = 0
        if (tag == "") {
            return info.items.size
        }

        for (list in info.items) {
            if (list.tags.contains(tag)) {
                element++
            }
        }
        return element
    }

    override fun getItemCount(): Int {
        return 10
    }

}