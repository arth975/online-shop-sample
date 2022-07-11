package ru.spoonbill.data.product.repository

import ru.spoonbill.data.product.model.Product
import ru.spoonbill.data.product.model.Promotion

interface ProductRepository {

    suspend fun fetchPopularProducts(): List<Product>

    suspend fun fetchRecommendedProducts(): List<Product>

    suspend fun fetchActualProducts(): List<Product>

    suspend fun fetchPromotions(): List<Promotion>
}