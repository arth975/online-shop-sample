package ru.spoonbill.data.product.repository

import ru.spoonbill.data.product.data_source.ProductDataSource
import ru.spoonbill.data.product.model.Product
import ru.spoonbill.data.product.model.Promotion

internal class ProductRepositoryImpl(
    private val mDataSource: ProductDataSource
) : ProductRepository {
    override suspend fun fetchPopularProducts(): List<Product> {
        return mDataSource.fetchProducts()
    }

    override suspend fun fetchRecommendedProducts(): List<Product> {
        return mDataSource.fetchProducts()
    }

    override suspend fun fetchActualProducts(): List<Product> {
        return mDataSource.fetchProducts()
    }

    override suspend fun fetchPromotions(): List<Promotion> {
        return mDataSource.fetchPromotions()
    }

}