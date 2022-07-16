package ru.spoonbill.droid.data.core.entity

sealed class NetworkResult {
    object Success : NetworkResult()
    data class Failure(val e: Exception) : NetworkResult()
}
