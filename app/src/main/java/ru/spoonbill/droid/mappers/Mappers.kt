package ru.spoonbill.droid.mappers

import ru.spoonbill.droid.data.core.features.product.entity.Product
import ru.spoonbill.droid.data.core.features.product.entity.Promotion
import ru.spoonbill.droid.ui.home.entity.ProductUi
import ru.spoonbill.droid.ui.home.entity.PromotionUi

fun Product.toUi() = ProductUi(
    id = this.id,
    productId = this.productId,
    name = this.name,
    description = this.description,
    composition = this.composition,
    price = this.price,
    amount = this.amount,
    unit = this.unit,
    caloriesAmount = this.caloriesAmount,
    proteinAmount = this.proteinAmount,
    fatsAmount = this.fatsAmount,
    carbsAmount = this.carbsAmount,
    bestBeforeDate = this.bestBeforeDate,
    minStorageTemp = this.minStorageTemp,
    maxStorageTemp = this.maxStorageTemp,
    manufacturer = this.manufacturer,
    categoryId = this.categoryId,
    imageUrl = this.imageUrl,
    quantityInCart = this.quantityInCart,
    isFavorite = this.isFavorite,
)

fun ProductUi.toModel() = Product(
    id = this.id,
    productId = this.productId,
    name = this.name,
    description = this.description,
    composition = this.composition,
    price = this.price,
    amount = this.amount,
    unit = this.unit,
    caloriesAmount = this.caloriesAmount,
    proteinAmount = this.proteinAmount,
    fatsAmount = this.fatsAmount,
    carbsAmount = this.carbsAmount,
    bestBeforeDate = this.bestBeforeDate,
    minStorageTemp = this.minStorageTemp,
    maxStorageTemp = this.maxStorageTemp,
    manufacturer = this.manufacturer,
    categoryId = this.categoryId,
    imageUrl = this.imageUrl,
    quantityInCart = this.quantityInCart,
    isFavorite = this.isFavorite,
)

fun Promotion.toUi() = PromotionUi(
    id = this.id,
    description = this.description,
    uri = this.uri
)