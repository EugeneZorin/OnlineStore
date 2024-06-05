package com.example.characteristic

import androidx.activity.viewModels
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.catalog.entity.CatalogItem
import com.example.catalog.entity.CatalogProductEntity
import com.example.characteristic.databinding.CharacteristicActivityBinding

class CharacteristicActivity : AppCompatActivity() {

    private lateinit var binding: CharacteristicActivityBinding
    private val viewModel: CharacteristicViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacteristicActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val catalogItem: CatalogItem? = intent.getParcelableExtra("item")

    }


}