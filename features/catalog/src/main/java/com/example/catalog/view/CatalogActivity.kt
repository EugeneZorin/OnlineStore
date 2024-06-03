package com.example.catalog.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catalog.adapters.CatalogAdapter
import com.example.catalog.contract.NavigationCharacteristic
import com.example.catalog.databinding.ActivityCatalogBinding
import com.example.catalog.entity.EntityData
import com.example.catalog.view.components.ButtonSetup
import com.example.catalog.view.components.ShowSortMenu
import com.example.catalog.viewmodel.CatalogViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding
    private lateinit var adapterCatalog: CatalogAdapter
    private lateinit var buttonSetup: ButtonSetup
    private lateinit var showSortMenu: ShowSortMenu

    private val viewModel: CatalogViewModel by viewModels()
    private val entityData = EntityData()

    @Inject
    lateinit var navigationCharacteristic: NavigationCharacteristic
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)


        init()
    }

    private fun init() {

        binding.catalogItem.layoutManager = GridLayoutManager(this, 2)

        buttonSetup = ButtonSetup(this, binding) { tag ->
            adapterCatalog.updateChosenTag(tag)
        }

        showSortMenu = ShowSortMenu(binding, this) { filter ->
            adapterCatalog.updateChosenFilter(filter)
        }


        binding.sortButton.setOnClickListener {
            showSortMenu.showSortMenu()
        }

        // CatalogAdapter startup
        viewModel.bitmapAndCatalogItem.observe(this) { (data, map) ->
            adapterCatalog = CatalogAdapter(data, map, navigationCharacteristic)
            adapterCatalog.updateChosenTag(entityData.tagSeeAll)
            binding.catalogItem.adapter = adapterCatalog
        }

        buttonSetup.initButtons()



    }

}


