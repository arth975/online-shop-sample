package ru.spoonbill.droid.ui.cart.model

data class CartUi(
    val id: Long,
    val deliveryAddress: String?,
    val products: List<CartProductUi>
)
