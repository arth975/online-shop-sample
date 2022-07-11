package ru.spoonbill.data.product.data_source

import ru.spoonbill.data.product.model.Product
import ru.spoonbill.data.product.model.Promotion

internal interface ProductDataSource {

    suspend fun fetchProducts(): List<Product>

    suspend fun fetchPromotions(): List<Promotion>
}