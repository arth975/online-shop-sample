package ru.spoonbill.data.product.data_source

import kotlinx.coroutines.delay
import ru.spoonbill.data.product.model.Product
import ru.spoonbill.data.product.model.Promotion

private val products = listOf(
    Product(1, "Продукт 1", 100f, false),
    Product(2, "Продукт 1", 100f, false),
    Product(3, "Продукт 1", 100f, false),
    Product(4, "Продукт 1", 100f, false),
    Product(5, "Продукт 1", 100f, false),
)

private val promotions = listOf(
    Promotion("https://www.guidingtech.com/wp-content/uploads/HD-Mouth-Watering-Food-Wallpapers-for-Desktop-12_4d470f76dc99e18ad75087b1b8410ea9.jpg"),
    Promotion("https://i.pinimg.com/originals/f0/b6/15/f0b615f78dd809d68ec389f4bc8d94bb.jpg"),
    Promotion("https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80"),
)

internal class ProductRemoteDataSource : ProductDataSource {
    override suspend fun fetchProducts(): List<Product> {
        delay(3000)
        return products
    }

    override suspend fun fetchPromotions(): List<Promotion> {
        delay(3000)
        return promotions
    }
}