package com.example.catalog.holder

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.catalog.adapters.ImageAdapter
import com.example.catalog.contract.NavigationCharacteristic
import com.example.catalog.databinding.CatalogItemBinding
import com.example.catalog.entity.Item

class ItemHolder(private val binding: CatalogItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: Item, bitmap: Bitmap?, navigationCharacteristic: NavigationCharacteristic) {
        with(binding) {

            // Handling a click on an element
            price.text = item.price.price
            priceWithDiscount.text = item.price.priceWithDiscount
            unit.text = item.price.unit
            discount.text = "-${item.price.discount} %"
            title.text = item.title
            descriptions.text = item.description
            rating.text = item.feedback.rating.toString()
            count.text = "(${item.feedback.count})"

            // Installing an adapter for ViewPager with an image
            viewProduct.adapter = ImageAdapter(itemView.context, bitmap)

            // Handling a click on an element
            productItem.setOnClickListener {
                navigationCharacteristic.navigationCharacteristic(
                    bitmap,
                    item.toCatalogItem()
                )
            }
        }
    }
}
