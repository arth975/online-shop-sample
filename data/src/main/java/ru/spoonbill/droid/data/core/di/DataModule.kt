package ru.spoonbill.droid.data.core.di

import org.koin.dsl.module
import ru.spoonbill.droid.data.core.config.di.configModule
import ru.spoonbill.droid.data.core.features.cart.di.cartModule
import ru.spoonbill.droid.data.core.features.product.di.productModule

val dataModule = module {
    includes(
        listOf(
            cartModule,
            configModule,
            productModule,
        )
    )
}