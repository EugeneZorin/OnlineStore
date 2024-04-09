package com.example.catalog.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catalog.databinding.ActivityCatalogBinding


class CatalogActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding
    private val adapter = CatalogAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){
        binding.catalogItem.layoutManager =  GridLayoutManager(this, 2)
        binding.catalogItem.adapter = adapter
    }

}