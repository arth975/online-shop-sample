package ru.spoonbill.droid.data.core.features.cart.model

data class Cart(
    val id: Long = 0,
    val totalPrice: Float = 0f,
    val products: List<CartProduct> = emptyList(),
    val deliveryAddress: String? = null,
)