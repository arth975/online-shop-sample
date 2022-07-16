package ru.spoonbill.droid.data.core.features.product.remote.data_source

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.spoonbill.droid.data.core.config.network.Paths
import ru.spoonbill.droid.data.core.config.network.client
import ru.spoonbill.droid.data.core.features.product.remote.entity.ProductResponse
import ru.spoonbill.droid.data.core.features.product.entity.Promotion

private val promotions = listOf(
    Promotion(1,"Акции","https://www.guidingtech.com/wp-content/uploads/HD-Mouth-Watering-Food-Wallpapers-for-Desktop-12_4d470f76dc99e18ad75087b1b8410ea9.jpg"),
    Promotion(2,"Что нового в Самоходе?", "https://i.pinimg.com/originals/f0/b6/15/f0b615f78dd809d68ec389f4bc8d94bb.jpg"),
    Promotion(3,"Что покупать в этот сезон?", "https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80"),
)

internal class ProductRemoteDataSourceImpl : ProductRemoteDataSource {
    override suspend fun getProducts(): List<ProductResponse> {
        return emptyList()// client.get(Paths.PRODUCTS).body()
    }

    override suspend fun getPromotions(): List<Promotion> {
        return promotions
    }

    override suspend fun getProductsByCategoryId(categoryId: Long): List<ProductResponse> {
        return client.get(Paths.PRODUCTS_BY_CATEGORY) {
            url {
                parameters.append("categoryId", categoryId.toString())
            }
        }.body()
    }
}