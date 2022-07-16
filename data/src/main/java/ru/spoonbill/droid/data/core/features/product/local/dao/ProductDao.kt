package ru.spoonbill.droid.data.core.features.product.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.spoonbill.droid.data.core.features.product.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<ProductEntity>)

    @Update
    fun update(cartProduct: ProductEntity)

    @Query("DELETE FROM products")
    fun cleanUp()

    @Query("SELECT * FROM products WHERE isFavorite = 1")
    fun getFavoriteProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE quantityInCart > 0")
    fun getCartProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products")
    fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM products")
    fun getFlow(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE productId = :productId")
    fun exists(productId: Long): Boolean
}