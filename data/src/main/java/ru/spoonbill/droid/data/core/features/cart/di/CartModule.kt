package ru.spoonbill.droid.data.core.features.cart.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.spoonbill.droid.data.core.config.database.SpoonbillDatabase
import ru.spoonbill.droid.data.core.features.cart.local.dao.CartDao
import ru.spoonbill.droid.data.core.features.cart.repository.CartRepository
import ru.spoonbill.droid.data.core.features.product.local.dao.ProductDao
import ru.spoonbill.droid.data.core.features.cart.repository.CartRepositoryImpl

internal val cartModule = module {
    single { provideCartDao(database = get()) } bind CartDao::class
    single { provideCartProductDao(database = get()) } bind ProductDao::class

    single { CartRepositoryImpl() } bind CartRepository::class
}

private fun provideCartDao(database: SpoonbillDatabase): CartDao = database.cartDao()
private fun provideCartProductDao(database: SpoonbillDatabase): ProductDao = database.productDao()