package com.example.catalog.presentation

import com.example.catalog.entity.EntityData
import com.example.catalog.entity.Item

class Filter {

    private val entityData = EntityData()
    fun filter(filter: String) {

        when (filter) {
            entityData.byPopularity -> filterByPopularity()
            entityData.onPrice -> filterOnPrice()
            entityData.byPrice -> filterByPrice()
        }

    }

    private fun filterByPopularity(): List<Item> {
        return listOf()
    }

    private fun filterOnPrice(): List<Item> {
        return listOf()
    }

    private fun filterByPrice(): List<Item> {
        return listOf()
    }

}