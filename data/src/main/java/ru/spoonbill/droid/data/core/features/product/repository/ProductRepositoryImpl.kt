package ru.spoonbill.droid.data.core.features.product.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.spoonbill.droid.data.core.entity.NetworkResult
import ru.spoonbill.droid.data.core.features.product.entity.Product
import ru.spoonbill.droid.data.core.features.product.entity.Promotion
import ru.spoonbill.droid.data.core.features.product.entity.toEntity
import ru.spoonbill.droid.data.core.features.product.entity.toModel
import ru.spoonbill.droid.data.core.features.product.local.dao.ProductDao
import ru.spoonbill.droid.data.core.features.product.local.entity.ProductEntity
import ru.spoonbill.droid.data.core.features.product.remote.data_source.ProductRemoteDataSource
import ru.spoonbill.droid.data.core.features.product.remote.entity.ProductResponse

internal class ProductRepositoryImpl(
    private val remoteDataSource: ProductRemoteDataSource,
    private val dao: ProductDao
) : ProductRepository {

    override suspend fun getAndCacheAllProducts(
        dispatcher: CoroutineDispatcher
    ): NetworkResult = withContext(dispatcher) {
        if (dao.getAll().isEmpty()) {
            try {
                val response = remoteDataSource.getProducts().map(ProductResponse::toEntity)
                dao.insertAll(response)
                return@withContext NetworkResult.Success
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext NetworkResult.Failure(e)
            }
        }
        return@withContext NetworkResult.Success
    }

    override suspend fun getPopularProductsFlow(): Flow<List<Product>> =
        dao.getFlow().map { it.map(ProductEntity::toModel) }

    override suspend fun getRecommendedProductsFlow(): Flow<List<Product>> =
        dao.getFlow().map { it.map(ProductEntity::toModel) }

    override suspend fun getActualProductsFlow(): Flow<List<Product>> =
        dao.getFlow().map { it.map(ProductEntity::toModel) }

    override suspend fun getPromotions(
        dispatcher: CoroutineDispatcher
    ): List<Promotion> = withContext(dispatcher) {
        remoteDataSource.getPromotions()
    }

    override suspend fun getFavoriteProductsFlow(
        dispatcher: CoroutineDispatcher
    ): Flow<List<Product>> = withContext(dispatcher) {
        dao.getFavoriteProducts().map { it.map(ProductEntity::toModel) }
    }

    override suspend fun getCartProductsFlow(
        dispatcher: CoroutineDispatcher
    ): Flow<List<Product>> = withContext(dispatcher){
        dao.getCartProducts().map { it.map(ProductEntity::toModel) }
    }

    override suspend fun updateProduct(
        product: Product,
        dispatcher: CoroutineDispatcher
    ) = withContext(dispatcher) {
        dao.update(product.toEntity())
    }
}