package ru.spoonbill.droid.ui.home.entity

data class ProductUi(
    val id: Long,
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
    val quantityInCart: Int,
    val isFavorite: Boolean,
)
