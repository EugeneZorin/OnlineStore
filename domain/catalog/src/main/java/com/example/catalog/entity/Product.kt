package com.example.catalog.entity

data class Price(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
)

data class Feedback(
    val count: Int,
    val rating: Double
)

data class Info(
    val title: String,
    val value: String
)

data class Item(
    val id: String,
    val title: String,
    val subtitle: String,
    val price: Price,
    val feedback: Feedback,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<Info>,
    val ingredients: String
)

data class ItemsDomain(
    val items: List<Item>
)
