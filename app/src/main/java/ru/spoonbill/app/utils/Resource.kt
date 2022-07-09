package ru.spoonbill.app.utils

sealed class Resource<T> {

    data class Success<T>(val data: T) : Resource<T>()

    data class Failure<T>(val e: Exception) : Resource<T>()

    class Loading<T> : Resource<T>()

    companion object {
        fun <T> success(data: T) = Success(data)

        fun <T> failure(e: Exception) = Failure<T>(e)

        fun <T> loading() = Loading<T>()
    }
}
