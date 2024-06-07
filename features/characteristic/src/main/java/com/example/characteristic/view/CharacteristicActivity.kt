package com.example.characteristic.view

import android.graphics.Paint
import androidx.activity.viewModels
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catalog.entity.CatalogItem
import com.example.characteristic.R
import com.example.characteristic.adapter.ImageAdapterCharacteristic
import com.example.characteristic.viewmodel.CharacteristicViewModel
import com.example.characteristic.databinding.CharacteristicActivityBinding

class CharacteristicActivity : AppCompatActivity() {

    private lateinit var binding: CharacteristicActivityBinding
    private val viewModel: CharacteristicViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacteristicActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        image()
        info()

    }

    private fun image(){
        binding.productPicture.adapter = ImageAdapterCharacteristic(this, viewModel.bitmap.value)
    }

    private fun info(){
        val catalogItem: CatalogItem? = intent.getParcelableExtra("item")

        catalogItem?.let { item ->
            with(binding) {
                title.text = item.title
                subheading.text = item.subtitle
                availableProducts.text = applicationContext.getString(R.string.available_products, item.available)

                rating.rating = item.feedback.rating.toFloat()
                productRating.text = item.feedback.rating.toString()
                reviewNumber.text = item.feedback.count.toString()

                newPrice.text = item.price.price
                oldPrice.apply {
                    text = item.price.priceWithDiscount
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }
                // BUG
                discountPercentage.text = applicationContext.getString(R.string.discount, item.available)

                buttonText.text = item.title
                descriptionTextView.text = item.description

                number.text = item.info.getOrNull(0)?.value ?: ""
                utilization.text = item.info.getOrNull(1)?.value ?: ""
                country.text = item.info.getOrNull(2)?.value ?: ""
                ingredients.text = item.ingredients
            }
        }

    }


}