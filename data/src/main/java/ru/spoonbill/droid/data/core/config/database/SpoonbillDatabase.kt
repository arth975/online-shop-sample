package ru.spoonbill.droid.data.core.config.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.spoonbill.droid.data.core.config.database.SpoonbillDatabase.Companion.DB_VERSION
import ru.spoonbill.droid.data.core.features.cart.local.dao.CartDao
import ru.spoonbill.droid.data.core.features.cart.local.enitity.CartEntity
import ru.spoonbill.droid.data.core.features.product.entity.mockedProducts
import ru.spoonbill.droid.data.core.features.product.local.dao.ProductDao
import ru.spoonbill.droid.data.core.features.product.local.entity.ProductEntity
import java.util.concurrent.Executors

@Database(entities = [CartEntity::class, ProductEntity::class], version = DB_VERSION)
abstract class SpoonbillDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao
    abstract fun productDao(): ProductDao

    companion object {
        internal const val DB_VERSION = 6
        private const val DB_NAME = "ru.spoonbill.app.DATABASE"

        private var INSTANCE: SpoonbillDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SpoonbillDatabase {
            if(INSTANCE == null) {
                INSTANCE = buildDatabase(context)
            }

            return INSTANCE ?: throw NullPointerException("The ${SpoonbillDatabase::class.simpleName} instance must not be null!")
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            SpoonbillDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadScheduledExecutor().execute {
                        getInstance(context).productDao().insertAll(mockedProducts)
                    }
                }
            })
            .build()
    }
}