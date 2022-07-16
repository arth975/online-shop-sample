package ru.spoonbill.droid.data.core.config.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

internal val client = HttpClient(CIO) {
    configDefaultRequest()
    configSerialization()
    configLogging()
}

private fun HttpClientConfig<CIOEngineConfig>.configDefaultRequest() {
    defaultRequest {
        configUrl()
        contentType(ContentType.Application.Json)
    }
}

private fun DefaultRequest.DefaultRequestBuilder.configUrl() {
    url {
        protocol = Paths.PROTOCOL
        host = Paths.HOST
    }
}

private fun HttpClientConfig<CIOEngineConfig>.configSerialization() {
    install(ContentNegotiation) {
        json()
    }
}

private fun HttpClientConfig<CIOEngineConfig>.configLogging() {
    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.BODY
    }
}