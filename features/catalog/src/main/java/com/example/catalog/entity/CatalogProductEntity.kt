package com.example.catalog.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogProductEntity(
    val items: List<CatalogItem> = emptyList()
) : Parcelable

@Parcelize
data class CatalogItem(
    val id: String = "",
    val title: String = "",
    val subtitle: String = "",
    val price: CatalogPrice = CatalogPrice(),
    val feedback: CatalogFeedback = CatalogFeedback(),
    val tags: List<String> = emptyList(),
    val available: Int = 0,
    val description: String = "",
    val info: List<CatalogInfo> = emptyList(),
    val ingredients: String = ""
) : Parcelable

@Parcelize
data class CatalogPrice(
    val price: String = "",
    val discount: Int = 0,
    val priceWithDiscount: String = "",
    val unit: String = ""
) : Parcelable

@Parcelize
data class CatalogFeedback(
    val count: Int = 0,
    val rating: Double = 0.0
) : Parcelable

@Parcelize
data class CatalogInfo(
    val title: String = "",
    val value: String = ""
) : Parcelable

