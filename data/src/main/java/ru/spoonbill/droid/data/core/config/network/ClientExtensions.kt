package ru.spoonbill.droid.data.core.config.network

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

fun HttpRequestBuilder.setPath(path: String) {
    url {
        path(path)
    }
}

suspend inline fun <reified T> HttpClient.post(path: String, request: T): HttpResponse = post {
    setPath(path)
    setBody(request)
}

suspend inline fun <reified T> HttpClient.put(path: String, request: T): HttpResponse = put {
    setPath(path)
    setBody(request)
}