package ru.spoonbill.droid.data.core.features.cart.model

import kotlinx.serialization.Serializable

@Serializable
data class CartProduct(
    val id: Long,
    val cartId: Long? = null,
    val productId: Long,
    val productName: String,
    val productPrice: Float,
    val productAmount: Int,
    val productQuantity: Int,
    val productImageUrl: String?,
    val isFavorite: Boolean,
    val isInCart: Boolean,
)
