package ru.spoonbill.droid.data.core.features.product.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import ru.spoonbill.droid.data.core.entity.NetworkResult
import ru.spoonbill.droid.data.core.features.cart.model.CartProduct
import ru.spoonbill.droid.data.core.features.product.entity.Product
import ru.spoonbill.droid.data.core.features.product.remote.entity.ProductResponse
import ru.spoonbill.droid.data.core.features.product.entity.Promotion

interface ProductRepository {

    suspend fun getAndCacheAllProducts(dispatcher: CoroutineDispatcher = Dispatchers.IO): NetworkResult

    suspend fun getPopularProductsFlow(): Flow<List<Product>>

    suspend fun getRecommendedProductsFlow(): Flow<List<Product>>

    suspend fun getActualProductsFlow(): Flow<List<Product>>

    suspend fun getPromotions(dispatcher: CoroutineDispatcher = Dispatchers.IO): List<Promotion>

    suspend fun getFavoriteProductsFlow(dispatcher: CoroutineDispatcher = Dispatchers.IO): Flow<List<Product>>

    suspend fun getCartProductsFlow(dispatcher: CoroutineDispatcher = Dispatchers.IO): Flow<List<Product>>

    suspend fun updateProduct(product: Product, dispatcher: CoroutineDispatcher = Dispatchers.IO)
}