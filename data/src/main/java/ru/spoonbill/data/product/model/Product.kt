package ru.spoonbill.data.product.model

data class Product(
    val id: Long,
    val name: String,
    val price: Float,
    val isFavorite: Boolean
)
