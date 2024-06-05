package com.example.catalog.holder

import com.example.catalog.entity.CatalogFeedback
import com.example.catalog.entity.CatalogInfo
import com.example.catalog.entity.CatalogItem
import com.example.catalog.entity.CatalogPrice
import com.example.catalog.entity.CatalogProductEntity
import com.example.catalog.entity.Feedback
import com.example.catalog.entity.Info
import com.example.catalog.entity.Item
import com.example.catalog.entity.Items
import com.example.catalog.entity.Price




fun Price.toCatalogPrice(): CatalogPrice {
    return CatalogPrice(
        price = this.price,
        discount = this.discount,
        priceWithDiscount = this.priceWithDiscount,
        unit = this.unit
    )
}


fun Feedback.toCatalogFeedback(): CatalogFeedback {
    return CatalogFeedback(
        count = this.count,
        rating = this.rating
    )
}



fun Info.toCatalogInfo(): CatalogInfo {
    return CatalogInfo(
        title = this.title,
        value = this.value
    )
}


fun Item.toCatalogItem(): CatalogItem {
    return CatalogItem(
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
        price = this.price.toCatalogPrice(),
        feedback = this.feedback.toCatalogFeedback(),
        tags = this.tags,
        available = this.available,
        description = this.description,
        info = this.info.map { it.toCatalogInfo() },
        ingredients = this.ingredients
    )
}

fun Items.toCatalogProductEntity(): CatalogProductEntity {
    return CatalogProductEntity(
        items = this.items.map { it.toCatalogItem() }
    )
}