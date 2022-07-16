package ru.spoonbill.droid.data.core.features.product.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productId: Long,
    val name: String,
    val description: String,
    val composition: String,
    val price: Float,
    val amount: Int,
    val unit: String,
    val caloriesAmount: Int,
    val proteinAmount: Float,
    val fatsAmount: Float,
    val carbsAmount: Float,
    val bestBeforeDate: String,
    val minStorageTemp: Int,
    val maxStorageTemp: Int,
    val manufacturer: String,
    val categoryId: Long,
    val imageUrl: String,
    val quantityInCart: Int = 0,
    val isFavorite: Boolean = false,
)