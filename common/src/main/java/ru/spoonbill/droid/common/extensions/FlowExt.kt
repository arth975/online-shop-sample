package ru.spoonbill.droid.common.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

suspend inline fun <T> Flow<T?>.nonNullCollect(crossinline action: suspend (T) -> Unit) =
    filter { item -> item != null }
        .collect { item -> action(item!!) }