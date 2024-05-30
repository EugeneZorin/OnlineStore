package com.example.catalog.entity

data class Items(
    val items: List<Item> = emptyList()
)

data class Item(
    val id: String = "",
    val title: String = "",
    val subtitle: String = "",
    val price: Price = Price(),
    val feedback: Feedback = Feedback(),
    val tags: List<String> = emptyList(),
    val available: Int = 0,
    val description: String = "",
    val info: List<Info> = emptyList(),
    val ingredients: String = ""
)

data class Price(
    val price: String = "",
    val discount: Int = 0,
    val priceWithDiscount: String = "",
    val unit: String = ""
)

data class Feedback(
    val count: Int = 0,
    val rating: Double = 0.0
)

data class Info(
    val title: String = "",
    val value: String = ""
)
