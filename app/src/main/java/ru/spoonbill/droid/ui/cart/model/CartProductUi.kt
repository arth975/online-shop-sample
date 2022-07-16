package ru.spoonbill.droid.ui.cart.model

data class CartProductUi(
    val id: Long,
    val cartId: Long?,
    val productId: Long,
    val productName: String,
    val productPrice: Float,
    val productAmount: Int,
    val productQuantity: Int,
    val productImageUrl: String?,
    val isFavorite: Boolean,
    val isInCart: Boolean,
)