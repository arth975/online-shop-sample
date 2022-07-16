package ru.spoonbill.droid.data.core.features.cart.local.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carts")
data class CartEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val deliveryAddress: String?,
)
