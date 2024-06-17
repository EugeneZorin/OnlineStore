package com.example.characteristic.view

import android.content.Intent
import android.graphics.Paint
import androidx.activity.viewModels
import android.os.Bundle
import android.view.View
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import com.example.catalog.entity.CatalogItem
import com.example.characteristic.R
import com.example.characteristic.adapter.ImageAdapterCharacteristic
import com.example.characteristic.viewmodel.CharacteristicViewModel
import com.example.characteristic.databinding.CharacteristicActivityBinding
import com.example.characteristic.entity.Entity

class CharacteristicActivity : AppCompatActivity() {

    private lateinit var binding: CharacteristicActivityBinding
    private val viewModel: CharacteristicViewModel by viewModels()
    private val entity: Entity = Entity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacteristicActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        image()
        info()
        description()
        ingredients()

    }

    private fun description(){
        with(binding){
            toggleTextView.setOnClickListener {
                descriptionTextView.visibility = if (descriptionTextView.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                toggleTextView.text = if (descriptionTextView.visibility == View.GONE) getString(R.string.disclose) else getString(R.string.hide)
            }
        }
    }

    private fun ingredients(){
        with(binding) {
            moreTextView.setOnClickListener {
                if (ingredients.maxLines == Integer.MAX_VALUE) {
                    ingredients.maxLines = 2
                    moreTextView.text = getString(R.string.more_details)
                } else {
                    ingredients.maxLines = Integer.MAX_VALUE
                    moreTextView.text = getString(R.string.show_less)
                }
            }
        }
    }

    private fun image(){
        binding.productPicture.adapter = ImageAdapterCharacteristic(this, viewModel.bitmap.value)
    }

    private fun info(){
        val catalogItem: CatalogItem? = intent.getParcelableExtra(entity.item)

        catalogItem?.let { item ->
            with(binding) {
                title.text = item.title
                subheading.text = item.subtitle
                availableProducts.text = applicationContext.getString(R.string.available_products, item.available)

                rating.rating = item.feedback.rating.toFloat()
                productRating.text = item.feedback.rating.toString()
                reviewNumber.text = applicationContext.getString(R.string.review, item.feedback.count)

                newPrice.text = applicationContext.getString(R.string.price, item.price.price.toInt())
                oldPrice.apply {
                    text = applicationContext.getString(R.string.price, item.price.priceWithDiscount.toInt())
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }

                discountPercentage.text = applicationContext.getString(R.string.discount, item.price.discount)

                buttonText.text = item.title
                descriptionTextView.text = item.description

                number.text = item.info.getOrNull(0)?.value ?: entity.emptiness
                utilization.text = item.info.getOrNull(1)?.value ?: entity.emptiness
                country.text = item.info.getOrNull(2)?.value ?: entity.emptiness
                ingredients.text = item.ingredients
            }
        }

    }


}