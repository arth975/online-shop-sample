package ru.spoonbill.droid.data.core.features.product.remote.data_source

import ru.spoonbill.droid.data.core.features.product.entity.Promotion
import ru.spoonbill.droid.data.core.features.product.remote.entity.ProductResponse

internal interface ProductRemoteDataSource {

    suspend fun getProducts(): List<ProductResponse>

    suspend fun getPromotions(): List<Promotion>

    suspend fun getProductsByCategoryId(categoryId: Long): List<ProductResponse>
}