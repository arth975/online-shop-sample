package ru.spoonbill.droid.data.core.features.cart.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.spoonbill.droid.data.core.features.cart.local.enitity.CartEntity
import ru.spoonbill.droid.data.core.features.product.local.entity.ProductEntity

data class CartWithProducts(
    @Embedded val cart: CartEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cartId"
    )
    val products: List<ProductEntity>,
)