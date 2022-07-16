package ru.spoonbill.droid.mappers

import ru.spoonbill.droid.data.core.features.cart.model.Cart
import ru.spoonbill.droid.data.core.features.cart.model.CartProduct
import ru.spoonbill.droid.ui.cart.model.CartProductUi
import ru.spoonbill.droid.ui.cart.model.CartUi
import ru.spoonbill.droid.ui.home.entity.ProductUi

fun Cart.toUi() = CartUi(
    id = id,
    deliveryAddress = deliveryAddress,
    products = products.map(CartProduct::toUi)
)

fun CartProduct.toUi() = CartProductUi(
    id = id,
    cartId = cartId,
    productId = productId,
    productName = productName,
    productPrice = productPrice,
    productAmount = productAmount,
    productQuantity = productQuantity,
    productImageUrl = productImageUrl,
    isFavorite = isFavorite,
    isInCart = isInCart,
)

fun CartProductUi.toModel() = CartProduct(
    id = id,
    cartId = cartId,
    productId = productId,
    productName = productName,
    productPrice = productPrice,
    productAmount = productAmount,
    productQuantity = productQuantity,
    productImageUrl = productImageUrl,
    isFavorite = isFavorite,
    isInCart = isInCart,
)

fun ProductUi.toCartProduct(
    cartId: Long,
    productQuantity: Int,
    cartProductId: Long = 0,
    isFavorite: Boolean = false,
    isInCart: Boolean = false,
) = CartProductUi(
    id = cartProductId,
    cartId = cartId,
    productId = id,
    productName = name,
    productPrice = price,
    productAmount = 0,
    productQuantity = productQuantity,
    productImageUrl = imageUrl,
    isFavorite = isFavorite,
    isInCart = isInCart,
)