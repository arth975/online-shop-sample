package ru.spoonbill.droid.data.core.features.product.entity

import ru.spoonbill.droid.data.core.features.product.local.entity.ProductEntity
import ru.spoonbill.droid.data.core.features.product.remote.entity.ProductResponse

internal fun ProductEntity.toModel() = Product(
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

internal fun Product.toEntity() = ProductEntity(
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


internal fun ProductResponse.toEntity() = ProductEntity(
    id = 0,
    productId = this.id,
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
    isFavorite = false,
)