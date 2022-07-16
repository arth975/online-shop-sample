package ru.spoonbill.droid.data.core.config.network

import io.ktor.http.*

internal object Paths {

    val PROTOCOL = URLProtocol.HTTPS
    const val HOST = "spoonbill-api.herokuapp.com"

    const val USER = "user"
    const val CREATE_QUEUE = "$USER/create_queue"

    const val PRODUCTS = "product"
    const val PRODUCTS_BY_CATEGORY = "product/by_category"

    const val CATEGORY = "category"
}