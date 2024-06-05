package com.example.characteristic.view

import androidx.activity.viewModels
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catalog.entity.CatalogItem
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

        with(binding) {
            title.text = catalogItem?.title
            subheading.text = catalogItem?.subtitle
            availableProducts.text = catalogItem?.available.toString()
            //  BUG
            rating.rating = catalogItem?.feedback?.rating!!.toFloat()
            productRating.text = catalogItem.feedback.rating.toString()
            reviewNumber.text = catalogItem.feedback.count.toString()

            //

            newPrice.text = catalogItem.price.price
            oldPrice.text = catalogItem.price.priceWithDiscount
            discountPercentage.text = catalogItem.price.discount.toString()

            buttonText.text = catalogItem.title
            descriptionTextView.text = catalogItem.description

            number.text = catalogItem.info[0].value
        }
    }


}